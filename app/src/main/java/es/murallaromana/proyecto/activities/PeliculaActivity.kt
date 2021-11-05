package es.murallaromana.proyecto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import es.murallaromana.proyecto.adpaters.ListaPeliculasAdapters
import es.murallaromana.proyecto.databinding.ActivityPeliculaBinding
import es.murallaromana.proyecto.modelos.dao.PeliculaDaoMockImpl


class PeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inflo las vistas
        binding = ActivityPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //obtengo los datos de los personajes
        val peliculaDao = PeliculaDaoMockImpl()
        val listaPeliculas = peliculaDao.getTodos()

        //Creo los componentes del RecyclerView
        val layoutManager =  LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapters(listaPeliculas)

        //Asocio el RVIEW con sus componentes
        binding.rvListaPeliculas.adapter = adapter
        binding.rvListaPeliculas.layoutManager = layoutManager

        binding.rvListaPeliculas.setHasFixedSize(true)
    }
}