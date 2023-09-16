interface TipoDeUsuario {
    fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean
}


object Par : TipoDeUsuario {

    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return !figurita.esPar() && !figurita.tieneJugadorConCamisetaPar() && !figurita.esSeleccionConCopasPar()
    }

}

class Nacionalista( var seleccionesAConservar :MutableList<Seleccion> ) : TipoDeUsuario {


    fun agregarSeleccion(seleccion: Seleccion){seleccionesAConservar.add(seleccion)}
    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return !seleccionesAConservar.contains(figurita.figuritaSeleccion())
    }

}

object Conservador: TipoDeUsuario {
    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return figurita.nivelDeImpresionAlta() && usuario.seLlenoElAlbum()
    }

}

object Fanatico : TipoDeUsuario {
    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return !usuario.esJugadorFavorito(figurita) || !figurita.esJugadorLeyenda()
    }
}

object Desprendido : TipoDeUsuario {
    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return true
    }
}

object Apostador : TipoDeUsuario {
    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return !figurita.onFire || !figurita.esJugadorPromesa()
    }
}

object Interesado : TipoDeUsuario {
    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return !usuario.topFive().contains(figurita)
    }
}

object Cambiante : TipoDeUsuario {

    fun criterioActual(usuario: Usuario) = if (usuario.edad() > 25) Conservador else Desprendido
    override fun puedeRegalar(figurita: Figurita,usuario: Usuario): Boolean {
        return criterioActual(usuario).puedeRegalar(figurita,usuario)
    }
}


    



