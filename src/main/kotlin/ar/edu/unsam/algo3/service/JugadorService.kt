package ar.edu.unsam.algo3.service

import BusinessException
import Jugador
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoJugador
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JugadorService {

    @Autowired
    lateinit var jugadorRepo: RepoJugador

    @Autowired
    lateinit var figuritaRepository: RepoFigurita

    fun getJugadores(campoDeBusqueda: String?) :List<Jugador> = jugadorRepo.searchByName(campoDeBusqueda)
    fun getJugadores(id: Int) = jugadorRepo.getById(id)

    fun getJugadoresDTO() :List<JugadorDTO> = jugadorRepo.allInstances().map { it.toDTO() }

    fun deleteJugador(id: Int) {
        val jugador = jugadorRepo.getById(id)
        if(figuritaRepository.allInstances().map { it.jugador }.contains(jugador)){
            throw BusinessException("El jugador está asociado a una figurita, y no puede ser eliminado")
        } else {
            jugadorRepo.delete(jugador)
        }
    }
}