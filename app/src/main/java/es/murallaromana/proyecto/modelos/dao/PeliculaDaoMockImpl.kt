package es.murallaromana.proyecto.modelos.dao

import es.murallaromana.proyecto.modelos.entidades.Pelicula

class PeliculaDaoMockImpl: PeliculaDao {
    override fun getTodos() = listOf(
        Pelicula("Titanic", "Romance", "Steven Spielberg", "10", "https://es.web.img3.acsta.net/medias/nmedia/18/86/91/41/19870073.jpg"),
        Pelicula("", "", "", "", ""),
        Pelicula("", "", "", "", ""),
        Pelicula("", "", "", "", ""))
}