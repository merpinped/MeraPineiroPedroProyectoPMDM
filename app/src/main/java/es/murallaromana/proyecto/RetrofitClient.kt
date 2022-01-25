package es.murallaromana.proyecto

import es.murallaromana.proyecto.modelos.dao.retrofit.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://damapi.herokuapp.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create()) // Solo si la api es un json
            .build()

        return retrofit
    }

    val apiRetrofit = getRetrofit().create(Api::class.java)
}