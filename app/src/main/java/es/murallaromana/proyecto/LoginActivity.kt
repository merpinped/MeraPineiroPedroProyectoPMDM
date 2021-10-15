package es.murallaromana.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegistrar = findViewById(R.id.btnRegistro)

        btnRegistrar.setOnClickListener() {
            var intent = Intent(this, RegistraActivity::class.java)
            startActivity(intent)
        }
    }
}