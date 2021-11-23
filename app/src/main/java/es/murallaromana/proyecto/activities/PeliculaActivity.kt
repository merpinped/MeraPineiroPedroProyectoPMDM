package es.murallaromana.proyecto.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.murallaromana.proyecto.App
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.adpaters.ListaPeliculasAdapters
import es.murallaromana.proyecto.databinding.ActivityPeliculaBinding
import es.murallaromana.proyecto.modelos.dao.PeliculaDaoMockImpl


class PeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculaBinding
    private lateinit var btnMas: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflo las vistas
        binding = ActivityPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Peliculas")
        // Obtengo los datos de las peliculas
        val listaPeliculas = App.pelicula

        // Creo los componentes del RecyclerView
        val layoutManager =  LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapters(listaPeliculas)

        // Asocio el RVIEW con sus componentes
        binding.rvPeliculas.adapter = adapter
        binding.rvPeliculas.layoutManager = layoutManager

        binding.rvPeliculas.setHasFixedSize(true)

        // Dividir el contenido de recycle view
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvPeliculas.context,
            layoutManager.orientation
        )
        binding.rvPeliculas.addItemDecoration(dividerItemDecoration)

        btnMas = findViewById(R.id.btnMas)
        btnMas.setOnClickListener() {
            val intent = Intent(this, DetallesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() { // Carga las imagenes cuando vuelve a la activity de pelicula
        super.onResume()

        val adapter = ListaPeliculasAdapters(App.pelicula)
        binding.rvPeliculas.adapter = adapter
    }
}