package es.murallaromana.proyecto.modelos.dao

import es.murallaromana.proyecto.modelos.entidades.Pelicula

class PeliculaDaoMockImpl: PeliculaDao {
    override fun getTodos() = listOf(
        Pelicula("Titanic", "Romance", "Steven Spielberg", "7", "https://es.web.img3.acsta.net/medias/nmedia/18/86/91/41/19870073.jpg"),
        Pelicula("El Hobbit: un viaje inesperado", "Fantasía", "Peter Jackson", "9", "https://pics.filmaffinity.com/El_Hobbit_Un_viaje_inesperado-249234415-large.jpg"),
        Pelicula("Pulp Fiction", "Thriller", "Quentin Tarantino", "10", "https://pics.filmaffinity.com/Pulp_Fiction-210382116-large.jpg"),
        Pelicula("Kingsman: Servicio Secreto", "Acción", "Matthew Vaughn", "9.5", "https://m.media-amazon.com/images/I/81Mi4-Qt4wL._SY445_.jpg"))
}