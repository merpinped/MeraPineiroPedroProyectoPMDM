package es.murallaromana.proyecto.modelos.dao

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import es.murallaromana.proyecto.RetrofitClient
import es.murallaromana.proyecto.activities.LoginActivity
import es.murallaromana.proyecto.activities.PeliculaActivity
import es.murallaromana.proyecto.modelos.entidades.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Start: Application() {

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = getSharedPreferences("datos", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "No encontrado")

        val llamadaApi1: Call<List<Pelicula>> = RetrofitClient.apiRetrofit.getPeliculas("Bearer $token")
        llamadaApi1.enqueue(object : Callback<List<Pelicula>> {
            override fun onResponse(
                call: Call<List<Pelicula>>, response: Response<List<Pelicula>>
            ) {
                if (response.code() > 299 || response.code() < 200) {
                    val editor = sharedPreferences.edit()
                    editor.putString("codeResponse", response.code().toString())
                    editor.apply()
                } else {
                    val editor = sharedPreferences.edit()
                    editor.putString("codeResponse", response.code().toString())
                    editor.apply()
                }
            }

            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Log.d("respuesta: onFailure", t.toString())
            }
        })
    }
}