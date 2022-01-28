package es.murallaromana.proyecto.modelos.dao.retrofit

import android.media.session.MediaSession
import es.murallaromana.proyecto.modelos.entidades.Pelicula
import es.murallaromana.proyecto.modelos.entidades.Token
import es.murallaromana.proyecto.modelos.entidades.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface UserService {
    @POST("users/signup")
    fun signup(@Body user: Usuario): Call<Unit>

    @POST("users/login")
    fun login(@Body user: Usuario): Call<Token>

    @GET("movies")
        fun getPeliculas(): Call<List<Pelicula>>
}