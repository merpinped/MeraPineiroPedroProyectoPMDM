package es.murallaromana.proyecto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.proyecto.R


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

        setTitle("Nuevo ususario")
        btnNuevoUsuario = findViewById(R.id.btnRegistro)
        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)
        tiConfirmar = findViewById(R.id.tiConfirmar)
        tiNombre = findViewById(R.id.tiNombre)
        tiTelefono = findViewById(R.id.tiTelefonoD)

        btnNuevoUsuario.setOnClickListener {
            val inicio = Intent(this, LoginActivity::class.java)
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
                    if (tiEmail.text.toString().contains("@gmail.com") || tiEmail.text.toString()
                            .split("@")[0] != "" // Comprueba si el formato del gmail es correcto
                    ) {
                        val sharedPref = getSharedPreferences(
                            "datos",
                            Context.MODE_PRIVATE
                        ) // Guardar objetos clave valor
                        val editor = sharedPref.edit() // Iniciamos una acción

                        editor.putString(
                            "email",
                            tiEmail.text.toString().trim()
                        ) // Elimina los espacios finales
                        editor.putString(
                            "password",
                            tiPassword.text.toString().trim()
                        )

                        editor.apply()
                        startActivity(inicio)
                        finish() // Cierra la acitvity para que no quede guardada y poder utilizar el shared pref

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