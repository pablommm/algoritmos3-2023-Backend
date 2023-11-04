package ar.edu.unsam.algo3.service

import Jugador
import ar.edu.unsam.algo3.repository.RepoJugador

class JugadorService {

    lateinit var jugadorRepo: RepoJugador

    fun getJugadores() :List<Jugador> = jugadorRepo.allInstances()
}