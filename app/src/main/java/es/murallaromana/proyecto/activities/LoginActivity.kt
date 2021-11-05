package es.murallaromana.proyecto.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.proyecto.R


class LoginActivity : AppCompatActivity() {

    private lateinit var btnRegistrar: Button
    private lateinit var btnLog: Button
    private lateinit var usuario: RegistraActivity
    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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