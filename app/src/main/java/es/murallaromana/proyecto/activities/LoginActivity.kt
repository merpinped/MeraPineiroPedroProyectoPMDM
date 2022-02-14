package es.murallaromana.proyecto.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.RetrofitClient
import es.murallaromana.proyecto.modelos.entidades.Token
import es.murallaromana.proyecto.modelos.entidades.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var btnNuevoUsuario: Button
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Desactiva el modo oscuro
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE)
        val codeResponse = sharedPreferences.getString("codeResponse", "Token inválido")

        if (codeResponse == "Token inválido") {
            setContentView(R.layout.activity_login) // Declarar el setContentView lo más tarde posible para que no me cargue el login si ya tiene token

            title = "Inicio de sesión"
            tiEmail = findViewById(R.id.tiEmail)
            tiPassword = findViewById(R.id.tiPassword)
            btnLogin = findViewById(R.id.btnLog)
            btnNuevoUsuario = findViewById(R.id.btnNuevoUsuario)



            btnLogin.setOnClickListener() {
                btnLogin.isEnabled = false
                val u = Usuario(tiEmail.text.toString(), tiPassword.text.toString())
                val loginCall = RetrofitClient.apiRetrofit.login(u)
                loginCall.enqueue(object : Callback<Token> {
                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        Log.d("respuesta: onFailure", t.toString())
                    }

                    @SuppressLint("CommitPrefEdits")
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        Log.d("respuesta: onResponse", response.toString())

                        if (response.code() > 299 || response.code() < 200) {
                            btnLogin.isEnabled = true
                            Toast.makeText(
                                this@LoginActivity,
                                "No se pudo iniciar sesión, usuario no registrado",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            val token = response.body()?.token
                            Log.d("respuesta: token:", token.orEmpty())

                            val editor = sharedPreferences.edit()
                            editor.putString("token", token)
                            editor.putString("codeResponse", response.code().toString())
                            editor.apply()

                            val inicio = Intent(this@LoginActivity, PeliculaActivity::class.java)
                            startActivity(inicio)
                            finish()
                        }
                    }
                })
            }

            btnNuevoUsuario.setOnClickListener() {
                btnNuevoUsuario.isEnabled = false

                val inicio = Intent(this, RegistraActivity::class.java)
                startActivity(inicio)
            }
        } else {
            val inicio = Intent(this@LoginActivity, PeliculaActivity::class.java)
            startActivity(inicio)
            finish()
        }
    }
}