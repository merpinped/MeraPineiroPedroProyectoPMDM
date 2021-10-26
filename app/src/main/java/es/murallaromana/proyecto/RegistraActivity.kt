package es.murallaromana.proyecto

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


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

        btnNuevoUsuario = findViewById(R.id.btnRegistro)
        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)
        tiConfirmar = findViewById(R.id.tiConfirmar)
        tiNombre = findViewById(R.id.tiNombre)
        tiTelefono = findViewById(R.id.tiTelefono)

        btnNuevoUsuario.setOnClickListener {

            // Comprueba si todos los campos estan completos
            if (TextUtils.equals(
                    tiEmail.text.toString(),
                    ""
                ) && TextUtils.equals(tiPassword.text.toString(), "")
                && TextUtils.equals(
                    tiConfirmar.text.toString(),
                    ""
                ) && TextUtils.equals(
                    tiNombre.text.toString(),
                    ""
                )
                && TextUtils.equals(tiTelefono.text.toString(), "")
            ) {

                Log.d("RegistrarActivity", "Campos vacíos")

            } else {
                if (TextUtils.equals(
                        tiPassword.text.toString(),
                        tiConfirmar.text.toString()
                    )
                ) { // Comprueba si confirmar y password son iguales

                    val sharedPref =
                        getPreferences(Context.MODE_PRIVATE) // Guardar objetos clave valor
                    val editor = sharedPref.edit() // Iniciamos una transacción

                    editor.putString("email", tiEmail.text.toString())
                    editor.putString("password", tiPassword.text.toString())

                    editor.apply()

                    onBackPressed() // Vuelve a la pantalla anterior
                } else {
                    Log.d("RegistrarActivity", "La contraseña y confirmar son distintas")
                }
            }
        }
    }

    fun getEmail(): String? {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        return preferences.getString("email", "")
    }

    fun getPassword(): String? {
        val preferences = getPreferences(Context.MODE_PRIVATE)
        return preferences.getString("password", "")
    }
}