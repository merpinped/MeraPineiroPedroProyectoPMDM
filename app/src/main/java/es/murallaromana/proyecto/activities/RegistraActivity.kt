package es.murallaromana.proyecto.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.RetrofitClient
import es.murallaromana.proyecto.modelos.dao.retrofit.UserService
import es.murallaromana.proyecto.modelos.entidades.Token
import es.murallaromana.proyecto.modelos.entidades.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegistraActivity : AppCompatActivity() {

    private lateinit var btnNuevoUsuario: Button
    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText
    private lateinit var tiConfirmar: TextInputEditText
    private lateinit var tiNombre: TextInputEditText
    private lateinit var tiTelefono: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registra)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Desactiva el modo oscuro

        title = "Nuevo ususario"
        btnNuevoUsuario = findViewById(R.id.btnRegistro)
        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)
        tiConfirmar = findViewById(R.id.tiConfirmar)
        tiNombre = findViewById(R.id.tiNombre)
        tiTelefono = findViewById(R.id.tiTelefonoD)

        btnNuevoUsuario.setOnClickListener {
            // Comprueba si todos los campos estan completos
            if (TextUtils.equals(
                    tiEmail.text.toString(),
                    ""
                ) || TextUtils.equals(
                    tiPassword.text.toString(),
                    ""
                ) || TextUtils.equals(
                    tiConfirmar.text.toString(),
                    ""
                ) || TextUtils.equals(
                    tiNombre.text.toString(),
                    ""
                )
                || TextUtils.equals(
                    tiTelefono.text.toString(),
                    ""
                )
            ) {
                Toast.makeText(this, "Uno de los campos está vacío", Toast.LENGTH_SHORT).show()
            } else {
                if (TextUtils.equals(
                        tiPassword.text.toString(),
                        tiConfirmar.text.toString()
                    )
                ) { // Comprueba si confirmar y password son iguales
                    if (tiEmail.text.toString().contains("@gmail.com") && tiEmail.text.toString()
                            .split("@")[0] != "" // Comprueba si el formato del gmail es correcto
                    ) {
                        val u = Usuario(tiEmail.text.toString(), tiPassword.text.toString())

                        val registroCall = RetrofitClient.apiRetrofit.signup(u)

                        registroCall.enqueue(object : Callback<Unit> {
                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("respuesta: onFailure", t.toString())
                            }

                            @SuppressLint("CommitPrefEdits")
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                Log.d("respuesta: onResponse", response.toString())

                                if (response.code() > 299 || response.code() < 200) {
                                    Toast.makeText(this@RegistraActivity, "No se pudo crear el usuario", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(this@RegistraActivity, "Usuario registrado", Toast.LENGTH_SHORT).show()
                                    onBackPressed()
                                }
                            }})
                    } else {
                        Toast.makeText(this, "Formato gmail incorrecto", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Las contraseñas son distintas", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}