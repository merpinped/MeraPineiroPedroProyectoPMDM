package es.murallaromana.proyecto.modelos.dao.retrofit

import es.murallaromana.proyecto.modelos.entidades.Pelicula
import es.murallaromana.proyecto.modelos.entidades.Token
import es.murallaromana.proyecto.modelos.entidades.Usuario
import retrofit2.Call
import retrofit2.http.*

interface UserService {
    @POST("users/signup")
    fun signup(@Body user: Usuario): Call<Unit>

    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>

    @GET("movies")
    fun getPeliculas(@Header("Authorization") token: String?): Call<MutableList<Pelicula>>

    @GET("movies")
    fun getById(@Header("Authorization") token: String?, @Path("id") id: String): Call<Pelicula>

    @POST("movies")
    fun createPeliculas(@Header("Authorization") token: String?, @Body pelicula: Pelicula): Call<Unit>

    @DELETE("movies")
    fun borrarPeliculas(@Path("id") id: String): Call<Unit>
}