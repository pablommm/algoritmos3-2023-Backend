package ar.edu.unsam.algo3.service

import Jugador
import ar.edu.unsam.algo3.repository.RepoJugador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JugadorService {

    @Autowired
    lateinit var jugadorRepo: RepoJugador

    fun getJugadores(campoDeBusqueda: String?) :List<Jugador> = jugadorRepo.searchByName(campoDeBusqueda)
    fun getJugadores(id: Int) = jugadorRepo.getById(id)

    fun deleteJugador(id: Int) {
        jugadorRepo.delete(jugadorRepo.getById(id))
    }
}