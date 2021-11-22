package es.murallaromana.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.databinding.ActivityDetallesBinding
import es.murallaromana.proyecto.modelos.entidades.Pelicula

class DetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding
    private var bandera = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val infoPelicula: Pelicula? = intent.extras?.get("pelicula") as Pelicula?

        if (infoPelicula != null) { // Si el objeto película viene vacío es una nueva película y los edit text están vacíos
            setTitle(infoPelicula.nombre) // Cambiamos el título de la pantalla

            binding.etTitulo.setText(infoPelicula.nombre)
            binding.etDirector.setText(infoPelicula.director)
            binding.etGenero.setText(infoPelicula.genero)
            binding.etNota.setText(infoPelicula.nota)
            binding.etResumen.setText(infoPelicula.resumen)
            binding.etUrl.setText(infoPelicula.url)
            Picasso.get().load(infoPelicula.url).into(binding.ivImagen)
        } else { // Si es una nueva película son todos editables
            title = "Nueva Película"
            Picasso.get().load("https://ichef.bbci.co.uk/news/640/amz/worldservice/live/assets/images/2011/07/25/110725144827_sp_question_mark_304x171_other_nocredit.jpg").into(binding.ivImagen)

            binding.etTitulo.isFocusableInTouchMode = true
            binding.etTitulo.isCursorVisible = true

            binding.etDirector.isFocusableInTouchMode = true
            binding.etDirector.isCursorVisible = true

            binding.etResumen.isFocusableInTouchMode = true
            binding.etResumen.isCursorVisible = true

            binding.etUrl.isFocusableInTouchMode = true
            binding.etUrl.isCursorVisible = true

            binding.etNota.isFocusableInTouchMode = true
            binding.etNota.isCursorVisible = true

            binding.etGenero.isFocusableInTouchMode = true
            binding.etGenero.isCursorVisible = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (title != "Nueva Película") {
            menuInflater.inflate(R.menu.menu_detalles_pelicula, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_editar) { // Guardar, action bar
            if (bandera) {
                item.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_24)

                binding.etTitulo.isFocusableInTouchMode = true
                binding.etTitulo.isCursorVisible = true

                binding.etDirector.isFocusableInTouchMode = true
                binding.etDirector.isCursorVisible = true

                binding.etResumen.isFocusableInTouchMode = true
                binding.etResumen.isCursorVisible = true

                binding.etUrl.isFocusableInTouchMode = true
                binding.etUrl.isCursorVisible = true

                binding.etNota.isFocusableInTouchMode = true
                binding.etNota.isCursorVisible = true

                binding.etGenero.isFocusableInTouchMode = true
                binding.etGenero.isCursorVisible = true

                bandera = false // Un marcador para cambiar el icono
            } else {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Borrar personaje")
                    .setMessage("Estas a punto de borrar un peruano")
                    .setPositiveButton("Aceptar") { dialog, id ->
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_edit)
                        bandera = true

                        binding.etTitulo.isFocusable = false
                        binding.etDirector.isFocusable = false
                        binding.etResumen.isFocusable= false
                        binding.etUrl.isFocusable = false
                        binding.etNota.isFocusable = false
                        binding.etGenero.isFocusable = false
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true
        } else if (item.itemId == R.id.accion_borrar) { // Borrar, action bar
            // Toast.makeText(this, "Personaje borrado", Toast.LENGTH_SHORT).show()

            val builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Borrar personaje")
                .setMessage("Estas a punto de borrar un peruano")
                .setPositiveButton("Aceptar") { dialog, id ->
                    finish()
                }
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.show()

            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}