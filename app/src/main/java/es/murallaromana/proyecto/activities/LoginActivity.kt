package es.murallaromana.proyecto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.proyecto.R


class LoginActivity : AppCompatActivity() {

    private lateinit var btnRegistrar: Button
    private lateinit var btnLog: Button
    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setTitle("Inicio de sesión")
        val sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE)

        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)

        val gmail = sharedPreferences.getString("email", "usuario@gmail.com")
        val password = sharedPreferences.getString("password", "contraseña")

        tiEmail.setText(gmail?.trim()) // Elimina los espacios en blanco finales
        tiPassword.setText(password?.trim())

        btnRegistrar = findViewById(R.id.btnUusario)
        btnRegistrar.setOnClickListener() {
            val intent = Intent(this, RegistraActivity::class.java)
            startActivity(intent)
             // Cierra la acitvity para que no quede guardada y poder utilizar el shared pref
        }

        btnLog = findViewById(R.id.btnLog)
        btnLog.setOnClickListener() {
            if (!gmail.equals("usuario@gmail.com") && !password.equals("contraseña")) {
                val intent = Intent(this, PeliculaActivity::class.java)
                startActivity(intent)
                finish() // Despues de loguearte si pulsas atras desde la lista se cierra la app
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT)
                    .show() // Si es el usuario y la contraseña de base te muestra un mensaje de incorrecto
            }
        }
    }
}