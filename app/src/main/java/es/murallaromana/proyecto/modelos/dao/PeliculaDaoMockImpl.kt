package es.murallaromana.proyecto.modelos.dao

import es.murallaromana.proyecto.modelos.entidades.Pelicula

class PeliculaDaoMockImpl: PeliculaDao {
    override fun getTodos() = listOf(
        Pelicula(
            "Titanic",
            "Romance",
            "Steven Spielberg",
            "7",
            "https://es.web.img3.acsta.net/medias/nmedia/18/86/91/41/19870073.jpg",
            ""
        ),
        Pelicula(
            "El Hobbit: un viaje inesperado",
            "Fantasía",
            "Peter Jackson",
            "9",
            "https://estatico.emisiondof6.com/recorte/n/dispficha/F3910707?od[]=Z1V:M_V",
            "La aventura cuenta el viaje de Bilbo Bolsón, quien se ve arrastrado a una épica búsqueda que le llevará a reclamar el reino que años atrás perdieron los enanos a manos del temible dragón Smaug. Sin verlo venir, y convencido por el mago Gandalf el Gris, Bilbo termina formando parte de una compañía de 13 enanos liderados por el legendario guerrero Thorin Escudo de Roble. Su viaje les llevará a lo salvaje, a través de tierras peligrosas infestadas de trasgos, orcos y huangos mortíferos, así como de una misteriosa y siniestra criatura conocida como El Nigromante.\n" +
                    "\n" +
                    "A pesar de que su destino está situado al Este, en las tierras yermas de la Montaña Solitaria, los personajes primero tendrán que escapar de los túneles de los trasgos, donde Bilbo conocerá a la criatura que cambiará su vida para siempre... Gollum.\n" +
                    "\n" +
                    "A orillas de un lago subterráneo será cuando el modesto Bilbo, al quedarse a solas con Gollum, descubra no solo la profundidad del ingenio y el coraje, hecho que le sorprende incluso a él, sino que además se apoderará del \"precioso\" anillo de Gollum, un anillo con inesperadas cualidades y de gran utilidad\u0085 Una sencilla sortija de oro que está ligada de tal manera al destino de toda la Tierra Media que Bilbo no es capaz siquiera de imaginar."
        ),
        Pelicula(
            "Pulp Fiction",
            "Thriller",
            "Quentin Tarantino",
            "10",
            "https://static.posters.cz/image/750/posters/pulp-fiction-cover-i1288.jpg",
            ""
        ),
        Pelicula(
            "Kingsman: Servicio Secreto",
            "Acción",
            "Matthew Vaughn",
            "9.5",
            "https://m.media-amazon.com/images/I/81Mi4-Qt4wL._SY445_.jpg",
            ""
        )
    )
}