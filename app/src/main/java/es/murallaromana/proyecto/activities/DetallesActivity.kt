package es.murallaromana.proyecto.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import es.murallaromana.proyecto.App
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.adpaters.ListaPeliculasAdapters
import es.murallaromana.proyecto.databinding.ActivityDetallesBinding
import es.murallaromana.proyecto.modelos.entidades.Pelicula
import org.w3c.dom.Text

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
            binding.etTelefonoD.setText(infoPelicula.telefono)
            Picasso.get().load(infoPelicula.url).into(binding.ivImagen)
        } else { // Si es una nueva película son todos editables
            title = "Nueva Película"
            Picasso.get()
                .load("https://ichef.bbci.co.uk/news/640/amz/worldservice/live/assets/images/2011/07/25/110725144827_sp_question_mark_304x171_other_nocredit.jpg")
                .into(binding.ivImagen)

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

            binding.etTelefonoD.isFocusableInTouchMode = true
            binding.etTelefonoD.isCursorVisible = true
        }

        binding.btAnhadir.setOnClickListener() {
            if (TextUtils.equals(
                    binding.etTitulo.text.toString(),
                    ""
                ) || TextUtils.equals(binding.etGenero.text.toString(), "") || TextUtils.equals(
                    binding.etDirector.text.toString(),
                    ""
                ) || TextUtils.equals(
                    binding.etNota.text.toString(),
                    ""
                )
                || TextUtils.equals(
                    binding.etUrl.text.toString(),
                    ""
                ) || TextUtils.equals(
                    binding.etResumen.text.toString(),
                    ""
                ) || TextUtils.equals(
                    binding.etTelefonoD.text.toString(),
                    ""
                )
            ) {
                Toast.makeText(this, "Uno de los campos está vacío", Toast.LENGTH_SHORT).show()
            } else {
                App.pelicula.add(
                    Pelicula(
                        binding.etTitulo.text.toString(),
                        binding.etGenero.text.toString(),
                        binding.etDirector.text.toString(),
                        binding.etNota.text.toString(),
                        binding.etUrl.text.toString(),
                        binding.etResumen.text.toString(),
                        binding.etTelefonoD.text.toString()
                    ) // https://upload.wikimedia.org/wikipedia/commons/5/54/Beaver-Szmurlo.jpg
                )

                finish()
            }
        }

        binding.etTelefonoD.setOnClickListener() { // Si clckas en el teléfono del director te lleva al teléfono para llamarle
            val telefono = binding.etTelefonoD.text.toString()
            val llamada = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse(telefono)
            startActivity(llamada)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (title != "Nueva Película") {
            menuInflater.inflate(R.menu.menu_detalles_pelicula, menu)
            binding.btAnhadir.isVisible = false
        } else {
            binding.btAnhadir.isVisible = true
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

                binding.etTelefonoD.isFocusableInTouchMode = true
                binding.etTelefonoD.isCursorVisible = true

                bandera = false // Un marcador para cambiar el icono
            } else {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Editar pelicula")
                    .setMessage("Estás a punto de editar una pelicula")
                    .setPositiveButton("Aceptar") { dialog, id ->
                        item.icon = ContextCompat.getDrawable(this, R.drawable.ic_edit)
                        bandera = true

                        binding.etTitulo.isFocusable = false
                        binding.etDirector.isFocusable = false
                        binding.etResumen.isFocusable = false
                        binding.etUrl.isFocusable = false
                        binding.etNota.isFocusable = false
                        binding.etGenero.isFocusable = false
                        binding.etTelefonoD.isFocusable = false

                        // Borramos la película actual y la substituimos por los nuevos datos
                        val position: Int? = intent.extras?.get("position") as Int?
                        if (position != null) {
                            App.pelicula.removeAt(position)
                            App.pelicula.add(
                                Pelicula(
                                    binding.etTitulo.text.toString(),
                                    binding.etGenero.text.toString(),
                                    binding.etDirector.text.toString(),
                                    binding.etNota.text.toString(),
                                    binding.etUrl.text.toString(),
                                    binding.etResumen.text.toString(),
                                    binding.etTelefonoD.text.toString()
                                )
                            )

                            finish()
                        }
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true
        } else if (item.itemId == R.id.accion_borrar) { // Borrar, action bar
            // Toast.makeText(this, "Personaje borrado", Toast.LENGTH_SHORT).show()

            val builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Borrar pelicula")
                .setMessage("Estás a punto de borrar una pelicula")
                .setPositiveButton("Aceptar") { dialog, id ->
                    val position: Int? = intent.extras?.get("position") as Int?
                    if (position != null) {
                        App.pelicula.removeAt(position)
                    }

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