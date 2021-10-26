package es.murallaromana.proyecto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    private lateinit var btnRegistrar: Button
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

        tiEmail = findViewById(R.id.tiEmail)
        tiPassword = findViewById(R.id.tiPassword)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("LoginActivity", "Estoy en el onBackPressed")
        val preferences = getPreferences(MODE_PRIVATE)
        tiEmail.setText(preferences.getString("email", ""))
    }
}