package ar.edu.unsam.algo3.service

import Jugador
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoJugador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class JugadorService {

    @Autowired
    lateinit var JugadorRepository: RepoJugador

    fun getJugadores() = JugadorRepository.allInstances()

    fun getJugador(id:Int) = JugadorRepository.getById(id)

    fun deleteJugador(id: Int) {
        JugadorRepository.delete(JugadorRepository.getById(id))
    }
    fun updateJugador(jugador: Jugador) = JugadorRepository.update(jugador)

    fun create(nuevoJugador: Jugador): Jugador { JugadorRepository.create(nuevoJugador)
        return nuevoJugador
    }

    fun getJugadorFiltrado(nombreABuscar: String) = JugadorRepository.search(nombreABuscar)

}