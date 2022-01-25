package es.murallaromana.proyecto.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.RetrofitClient
import es.murallaromana.proyecto.adpaters.ListaPeliculasAdapters
import es.murallaromana.proyecto.databinding.ActivityPeliculaBinding
import es.murallaromana.proyecto.modelos.dao.App
import es.murallaromana.proyecto.modelos.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculaBinding
    private lateinit var btnMas: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflo las vistas
        binding = ActivityPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrofit
        val llamadaApi: Call<List<Pelicula>> = RetrofitClient.apiRetrofit.getPeliculas()
        llamadaApi.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {
                Toast.makeText(binding.root.context, response.body().toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("Prueba", t.message.toString())
            }
        })

        title = "Peliculas"
        // Obtengo los datos de las peliculas
        val listaPeliculas = App.pelicula

        // Creo los componentes del RecyclerView
        val layoutManager = LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapters(listaPeliculas)

        // Asocio el RVIEW con sus componentes
        binding.rvPeliculas.adapter = adapter
        binding.rvPeliculas.layoutManager = layoutManager
    }

    override fun onResume() { // Carga las imagenes cuando vuelve a la activity de pelicula
        super.onResume()

        val adapter = ListaPeliculasAdapters(App.pelicula)
        binding.rvPeliculas.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lista_peliculas_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_add) {
            val intent = Intent(this, DetallesActivity::class.java)
            startActivity(intent)
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }
}