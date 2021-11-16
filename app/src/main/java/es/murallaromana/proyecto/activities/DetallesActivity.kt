package es.murallaromana.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.modelos.entidades.Pelicula

class DetallesActivity : AppCompatActivity() {

    private lateinit var etTitulo: TextInputEditText
    private lateinit var etDirector: TextInputEditText
    private lateinit var etGenero: TextInputEditText
    private lateinit var etNota: TextInputEditText
    private lateinit var etResumen: TextInputEditText
    private lateinit var ivImagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val infoPelicula: Pelicula = intent.extras?.get("pelicula") as Pelicula
        setTitle(infoPelicula.nombre) // Cambiamos el título de la pantalla

        if (infoPelicula != null) {
            etTitulo = findViewById(R.id.etTitulo)
            etTitulo.setText(infoPelicula.nombre)

            etDirector = findViewById(R.id.etDirector)
            etDirector.setText(infoPelicula.director)

            etGenero = findViewById(R.id.etGenero)
            etGenero.setText(infoPelicula.genero)

            etNota = findViewById(R.id.etNota)
            etNota.setText(infoPelicula.nota)

            etResumen = findViewById(R.id.etResumen)
            etResumen.setText(infoPelicula.resumen)

            ivImagen = findViewById(R.id.ivImagen)
            Picasso.get().load(infoPelicula.url).into(ivImagen)
        }
    }
}