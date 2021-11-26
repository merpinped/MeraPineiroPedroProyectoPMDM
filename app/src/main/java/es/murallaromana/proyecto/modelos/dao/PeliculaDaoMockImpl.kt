package es.murallaromana.proyecto.modelos.dao

import es.murallaromana.proyecto.modelos.entidades.Pelicula

class PeliculaDaoMockImpl : PeliculaDao {
    override fun getTodos() = mutableListOf(
        Pelicula(
            "Titanic",
            "Romance",
            "Steven Spielberg",
            "7",
            "https://es.web.img3.acsta.net/medias/nmedia/18/86/91/41/19870073.jpg",
            "Jack (DiCaprio), un joven artista, en una partida de cartas gana un pasaje para América, en el Titanic, el trasatlántico más grande y seguro jamás construido. A bordo, conoce a Rose (Kate Winslet), una joven de una buena familia venida a menos que va a contraer un matrimonio de conveniencia con Cal (Billy Zane), un millonario engreído a quien sólo interesa el prestigioso apellido de su prometida. Jack y Rose se enamoran, pero Cal y la madre de Rose ponen todo tipo de trabas a su relación. Inesperadamente, un inmenso iceberg pone en peligro la vida de los pasajeros.",
            "666777888"
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
                    "A orillas de un lago subterráneo será cuando el modesto Bilbo, al quedarse a solas con Gollum, descubra no solo la profundidad del ingenio y el coraje, hecho que le sorprende incluso a él, sino que además se apoderará del \"precioso\" anillo de Gollum, un anillo con inesperadas cualidades y de gran utilidad\u0085 Una sencilla sortija de oro que está ligada de tal manera al destino de toda la Tierra Media que Bilbo no es capaz siquiera de imaginar.",
            "668757188"
        ),
        Pelicula(
            "Pulp Fiction",
            "Thriller",
            "Quentin Tarantino",
            "10",
            "https://static.posters.cz/image/750/posters/pulp-fiction-cover-i1288.jpg",
            "Jules Winnfield (Samuel L. Jackson, Jackie Brown) y Vincent Vega (John Travolta, Grease) son dos asesinos a sueldo que trabajan a las órdenes del temido gángster Marsellus Wallace (Ving Rhames, Amanecer de los muertos), quien les encarga la importante misión de recuperar su misterioso maletín, que ha sido robado. Ahí empezarán todos sus problemas.\n" +
                    "\n" +
                    "Los dos criminales son polos opuestos que deberán trabajar juntos para cumplir su cometido. De forma paralela, Vincent tendrá que hacerse cargo de Mia Wallace (Uma Thurman, Kill Bill), la peculiar novia de su jefe, a petición del mismo, mientras él pasa unos días fuera de la ciudad. Su compañero Jules le recomienda que vaya con cautela, pues la atractiva mujer le puede meter en problemas. Mientras, el boxeador Butch Coolidge (Bruce Willis, El sexto sentido) debe perder una importante pelea, pues ha sido sobornado por Wallace para participar en este combate amañado, y la pareja formada por Pumpkin/Ringo (Tim Roth, Reservoir Dogs) y Honey Bunny/Yolanda (Amanda Plummer, Mi vida sin mí) decidirá atracar un establecimiento debido a su lamentable situación laboral.\n" +
                    "\n" +
                    "Esta película de culto escrita y dirigida por Quentin Tarantino (Reservoir Dogs, Malditos bastardos) se convirtió en todo un icono de la década de los noventa y en uno de los filmes mejor valorados de la historia. Además de los mencionados, cuenta en su reparto con Harvey Keitel (El gran hotel Budapest), Maria de Medeiros (Mi vida sin mí), Eric Stoltz (El efecto mariposa), Rosanna Arquette (El gran azul) y Christopher Walken (Atrápame si puedes).",
            "123456789"
        ),
        Pelicula(
            "Kingsman: Servicio Secreto",
            "Acción",
            "Matthew Vaughn",
            "9",
            "https://m.media-amazon.com/images/I/81Mi4-Qt4wL._SY445_.jpg",
            "Un veterano agente secreto inglés (Colin Firth) debe entrenar a un joven sin refinar (Taron Egerton), pero que promete convertirse en un competitivo agente gracias a un ultra-programa de entrenamiento, al mismo tiempo que una amenaza global emerge procedente de un genio retorcido. Adaptación del cómic de Mark Millar y Dave Gibbons.",
            "123456789"
        )
    )
}