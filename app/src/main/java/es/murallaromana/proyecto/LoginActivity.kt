package es.murallaromana.proyecto

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    private lateinit var btnRegistrar: Button
    private lateinit var usuario: RegistraActivity
    private lateinit var tiEmail: TextInputEditText
    private lateinit var tiPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegistrar = findViewById(R.id.btnRegistro)

        btnRegistrar.setOnClickListener() {
            val intent = Intent(this, RegistraActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)
    }
}