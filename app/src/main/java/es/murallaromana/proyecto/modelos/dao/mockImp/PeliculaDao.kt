package es.murallaromana.proyecto.modelos.dao.mockImp

import es.murallaromana.proyecto.modelos.entidades.Pelicula

interface PeliculaDao {
    fun getTodos(): MutableList<Pelicula>
}