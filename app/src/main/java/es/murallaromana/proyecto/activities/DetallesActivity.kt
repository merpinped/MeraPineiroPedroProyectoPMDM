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

        etTitulo = findViewById(R.id.etTitulo)
        etDirector = findViewById(R.id.etDirector)
        etGenero = findViewById(R.id.etGenero)
        etNota = findViewById(R.id.etNota)
        etResumen = findViewById(R.id.etResumen)
        ivImagen = findViewById(R.id.ivImagen)

        if (infoPelicula != null) {
            setTitle(infoPelicula.nombre) // Cambiamos el título de la pantalla
            etTitulo.setText(infoPelicula.nombre)
            etDirector.setText(infoPelicula.director)
            etGenero.setText(infoPelicula.genero)
            etNota.setText(infoPelicula.nota)
            etResumen.setText(infoPelicula.resumen)
            Picasso.get().load(infoPelicula.url).into(ivImagen)
        } else {
            setTitle("Nueva Película")
        }
    }
}