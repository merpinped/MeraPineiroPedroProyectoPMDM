package es.murallaromana.proyecto.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.RetrofitClient
import es.murallaromana.proyecto.adpaters.ListaPeliculasAdapters
import es.murallaromana.proyecto.databinding.ActivityPeliculaBinding
import es.murallaromana.proyecto.modelos.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PeliculaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculaBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflo las vistas
        binding = ActivityPeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Peliculas"
    }


    override fun onResume() { // Carga las imagenes cuando vuelve a la activity de pelicula
        super.onResume()

        // Retrofit
        sharedPreferences = getSharedPreferences("datos", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "No encontrado")

        val llamadaApi: Call<List<Pelicula>> = RetrofitClient.apiRetrofit.getPeliculas("Bearer $token")
        llamadaApi.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {
                if (response.code() > 299 || response.code() < 200) {
                    Toast.makeText(
                        this@PeliculaActivity,
                        "No se pudo cargar la lista de películas",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val listaPeliculas = response.body()!!

                    // Creo los componentes del RecyclerView
                    val layoutManager = LinearLayoutManager(this@PeliculaActivity)
                    val adapter = ListaPeliculasAdapters(listaPeliculas)

                    // Asocio el RVIEW con sus componentes
                    binding.rvPeliculas.adapter = adapter
                    binding.rvPeliculas.layoutManager = layoutManager
                }
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("response: failure", t.message.toString())
            }
        })
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