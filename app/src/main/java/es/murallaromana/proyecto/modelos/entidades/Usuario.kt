package es.murallaromana.proyecto.modelos.entidades

import com.google.gson.annotations.SerializedName

class Usuario(
    var id: Int?,
    var email: String,
    @SerializedName("password") var password: String
)
