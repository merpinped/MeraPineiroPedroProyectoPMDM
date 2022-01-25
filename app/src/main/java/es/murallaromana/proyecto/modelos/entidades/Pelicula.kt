package es.murallaromana.proyecto.modelos.entidades

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pelicula(
    @SerializedName("title") var nombre: String,
    var genero: String,
    var director: String,
    var nota: String,
    var url: String,
    var resumen: String,
    var telefono: String,
    @SerializedName("runtime") var tiempo: String
) : Serializable