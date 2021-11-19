package es.murallaromana.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    private lateinit var etUrl: TextInputEditText
    private lateinit var ivImagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val infoPelicula: Pelicula? = intent.extras?.get("pelicula") as Pelicula?

        etTitulo = findViewById(R.id.etTitulo)
        etDirector = findViewById(R.id.etDirector)
        etGenero = findViewById(R.id.etGenero)
        etNota = findViewById(R.id.etNota)
        etResumen = findViewById(R.id.etResumen)
        etUrl = findViewById(R.id.etUrl)
        ivImagen = findViewById(R.id.ivImagen)

        if (infoPelicula != null) {
            setTitle(infoPelicula.nombre) // Cambiamos el título de la pantalla
            etTitulo.setText(infoPelicula.nombre)
            etDirector.setText(infoPelicula.director)
            etGenero.setText(infoPelicula.genero)
            etNota.setText(infoPelicula.nota)
            etResumen.setText(infoPelicula.resumen)
            etUrl.setText(infoPelicula.url)
            Picasso.get().load(infoPelicula.url).into(ivImagen)
        } else {
            setTitle("Nueva Película")
            Picasso.get().load("https://ichef.bbci.co.uk/news/640/amz/worldservice/live/assets/images/2011/07/25/110725144827_sp_question_mark_304x171_other_nocredit.jpg").into(ivImagen)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalles_pelicula, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_guardar) { // Guardar, action bar
            Toast.makeText(this, "Personaje guardado", Toast.LENGTH_SHORT).show()

            return true
        } else if (item.itemId == R.id.accion_borrar) { // Borrar, action bar
            // Toast.makeText(this, "Personaje borrado", Toast.LENGTH_SHORT).show()

            val builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Borrar personaje")
                .setMessage("Estas a punto de borrar un peruano")
                .setPositiveButton("Aceptar", {dialog, id ->
                    finish()
                })
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.show()

            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}