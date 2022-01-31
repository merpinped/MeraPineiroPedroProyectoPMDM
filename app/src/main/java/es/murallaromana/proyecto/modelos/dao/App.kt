package es.murallaromana.proyecto.modelos.dao

import android.app.Application
import es.murallaromana.proyecto.modelos.dao.mockImp.PeliculaDaoMockImpl
import es.murallaromana.proyecto.modelos.entidades.Pelicula

class App: Application() {

    companion object {
        var pelicula: MutableList<Pelicula> = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()
        val dao = PeliculaDaoMockImpl()
        pelicula = dao.getTodos()
    }
}