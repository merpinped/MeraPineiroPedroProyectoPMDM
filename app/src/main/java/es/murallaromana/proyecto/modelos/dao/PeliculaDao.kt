package es.murallaromana.proyecto.modelos.dao

import es.murallaromana.proyecto.modelos.entidades.Pelicula

interface PeliculaDao {
    fun getTodos(): List<Pelicula>
}