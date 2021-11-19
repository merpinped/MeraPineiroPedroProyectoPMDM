package es.murallaromana.proyecto.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        tiEmail.setText(gmail)
        tiPassword.setText(password)

        btnRegistrar = findViewById(R.id.btnUusario)
        btnRegistrar.setOnClickListener() {
            val intent = Intent(this, RegistraActivity::class.java)
            startActivity(intent)
        }

        btnLog = findViewById(R.id.btnLog)
        btnLog.setOnClickListener() {
            val intent = Intent(this, PeliculaActivity::class.java)
            startActivity(intent)
        }
    }
}