package es.murallaromana.proyecto.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.RetrofitClient
import es.murallaromana.proyecto.databinding.ActivityDetallesBinding
import es.murallaromana.proyecto.modelos.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding
    private var bandera = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val infoPelicula = intent.extras?.get("peliculaId") as String?
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (infoPelicula != null) { // Si el string viene vacío es una nueva película y los edit text están vacíos
            val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
            val token = sharedPreferences.getString("token", "No encontrado")

            val llamadaApi: Call<Pelicula> =
                RetrofitClient.apiRetrofit.getById("Bearer $token", infoPelicula)
            llamadaApi.enqueue(object : Callback<Pelicula> {
                override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                    if (response.code() > 299 || response.code() < 200) {
                        Toast.makeText(
                            this@DetallesActivity,
                            "No se pudo cargar la pelicula",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val peliculaDetalles = response.body()!!

                        title = peliculaDetalles.nombre // Cambiamos el título de la pantalla

                        binding.etTitulo.setText(peliculaDetalles.nombre)
                        binding.etDirector.setText(peliculaDetalles.director)
                        binding.etGenero.setText(peliculaDetalles.genero)
                        binding.etNota.setText(peliculaDetalles.nota.toString())
                        binding.etResumen.setText(peliculaDetalles.resumen)
                        binding.etUrl.setText(peliculaDetalles.url)
                        binding.etTiempo.setText(peliculaDetalles.tiempo.toString())
                        binding.etTelefonoD.setText(peliculaDetalles.telefono)

                        Picasso.get().load(peliculaDetalles.url).into(binding.ivImagen)

                        binding.btLlamar.isEnabled = true
                    }
                }

                override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                    Log.d("response: failure", t.message.toString())
                }
            })

        } else { // Si es una nueva película son todos editables
            title = "Nueva Película"

            binding.btLlamar.isEnabled = false // No deja llamar mientras añades una película

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

            binding.etTiempo.isFocusableInTouchMode = true
            binding.etTiempo.isCursorVisible = true
        }

        binding.btLlamar.setOnClickListener() { // Si clickas en el teléfono del director te lleva al teléfono para llamarle
            val telefono = "+34" + binding.etTelefonoD.text.toString()
            val llamada = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", telefono, null))

            // intent.data = Uri.parse(telefono)
            startActivity(llamada)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (title != "Nueva Película") {
            menuInflater.inflate(R.menu.menu_detalles_pelicula, menu)
        } else {
            menuInflater.inflate(R.menu.menu_crear_pelicula, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_editar) {
            if (bandera) {
                binding.btLlamar.isEnabled = false

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

                binding.etTiempo.isFocusableInTouchMode = true
                binding.etTiempo.isCursorVisible = true

                bandera = false // Un marcador para cambiar el icono de edición a acepatar y viceversa
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
                        binding.etTiempo.isFocusable = false

                        // Actualizamos la película
                        val infoPelicula = intent.extras?.get("peliculaId") as String?
                        val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                        val token = sharedPreferences.getString("token", "No encontrado")
                        val pelicula = Pelicula(
                            binding.etTitulo.text.toString(),
                            binding.etGenero.text.toString(),
                            binding.etDirector.text.toString(),
                            binding.etNota.text.toString().toDouble(),
                            binding.etUrl.text.toString(),
                            binding.etResumen.text.toString(),
                            binding.etTelefonoD.text.toString(),
                            binding.etTiempo.text.toString().toInt(),
                            infoPelicula.toString()
                        )
                        val llamadaApi: Call<Unit> = RetrofitClient.apiRetrofit.actualizarPeliculas(
                            "Bearer $token",
                            pelicula
                        )
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                if (response.code() > 299 || response.code() < 200) {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "No se pudo actualizar la película",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Película actualizada con éxito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("response: failure", t.message.toString())
                            }
                        })
                        finish()
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true
        } else if (item.itemId == R.id.accion_borrar) {
            if (bandera) {
                val infoPelicula = intent.extras?.get("peliculaId") as String?
                val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "No encontrado")

                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Borrar pelicula")
                    .setMessage("Estás a punto de borrar una pelicula")
                    .setPositiveButton("Aceptar") { dialog, id ->
                        val llamadaApi: Call<Unit> =
                            RetrofitClient.apiRetrofit.borrarPeliculas(
                                "Bearer $token",
                                infoPelicula
                            )
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(
                                call: Call<Unit>,
                                response: Response<Unit>
                            ) {
                                if (response.code() > 299 || response.code() < 200) {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "No se pudo borrar la película",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Película borrada con éxito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("response: failure", t.message.toString())
                            }
                        })
                        finish()
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true
        } else if (item.itemId == R.id.accion_add) {
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
                ) || TextUtils.equals(
                    binding.etTiempo.text.toString(),
                    ""
                )
            ) {
                Toast.makeText(this, "Uno de los campos está vacío", Toast.LENGTH_SHORT).show()
            } else {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle("Crear nueva pelicula")
                    .setMessage("Estás a punto de crear una pelicula")
                    .setPositiveButton("Aceptar") { dialog, id ->
                        // Añadir en la api
                        val sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
                        val token = sharedPreferences.getString("token", "No encontrado")
                        val pelicula = Pelicula(binding.etTitulo.text.toString(), binding.etGenero.text.toString(), binding.etDirector.text.toString(), binding.etNota.text.toString().toDouble(),
                        binding.etUrl.text.toString(), binding.etResumen.text.toString(), binding.etTelefonoD.text.toString(), binding.etTiempo.text.toString().toInt(), null)

                        val llamadaApi: Call<Unit> = RetrofitClient.apiRetrofit.createPeliculas("Bearer $token", pelicula)
                        llamadaApi.enqueue(object : Callback<Unit> {
                            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                                if (response.code() > 299 || response.code() < 200) {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "No se pudo añadir la película",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@DetallesActivity,
                                        "Película añadida con éxito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<Unit>, t: Throwable) {
                                Log.d("response: failure", t.message.toString())
                            }
                        })


                        finish()
                    }
                    .setNegativeButton("Cancelar", null)
                    .create()

                dialog.show()
            }

            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}