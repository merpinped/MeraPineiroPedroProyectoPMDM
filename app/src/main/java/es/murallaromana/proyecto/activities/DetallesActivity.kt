package es.murallaromana.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.modelos.entidades.Pelicula

class DetallesActivity : AppCompatActivity() {

    private lateinit var etTitulo: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val infoPelicula: Pelicula = intent.extras?.get("pelicula") as Pelicula

        etTitulo = findViewById(R.id.etTitulo)
        etTitulo.setText(infoPelicula.nombre)
    }
}