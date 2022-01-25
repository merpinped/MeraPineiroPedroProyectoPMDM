package es.murallaromana.proyecto.modelos.dao.retrofit

import es.murallaromana.proyecto.modelos.entidades.Pelicula
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("movies")
    fun getPeliculas(): Call<List<Pelicula>>

    /*
    TODO: declarar todos los metodos del API siguiendo la documentacion
     */
}