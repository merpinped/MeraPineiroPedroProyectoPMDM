package es.murallaromana.proyecto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.proyecto.R


class LoginActivity : AppCompatActivity() {

    private lateinit var btnRegistrar: Button
    private lateinit var btnLog: Button
    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText
    private lateinit var gmail: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Desactiva el modo oscuro

        setTitle("Inicio de sesión")
        val sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE)

        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)

        gmail = sharedPreferences.getString("email", "usuario@gmail.com").toString()
        password = sharedPreferences.getString("password", "contraseña").toString()

        tiEmail.setText(gmail.trim()) // Elimina los espacios en blanco finales
        tiPassword.setText(password.trim())

        btnRegistrar = findViewById(R.id.btnUusario)
        btnRegistrar.setOnClickListener() {
            val intent = Intent(this, RegistraActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLog = findViewById(R.id.btnLog)
        btnLog.setOnClickListener() {
            if (gmail != "usuario@gmail.com" && password != "contraseña") {
                if (tiEmail.text.toString() == gmail && tiPassword.text.toString() == password) {
                    val intent = Intent(this, PeliculaActivity::class.java)
                    startActivity(intent)
                    finish() // Despues de loguearte si pulsas atras desde la lista se cierra la app
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Usuario o contraseña por defecto", Toast.LENGTH_SHORT)
                    .show() // Si es el usuario y la contraseña de base te muestra un mensaje de incorrecto
            }
        }
    }
}