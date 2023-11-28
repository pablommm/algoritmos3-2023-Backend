package ar.edu.unsam.algo3.service

import BusinessException
import Jugador
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

    fun deleteJugador(id: Int) {
        val jugador = jugadorRepo.getById(id)
        if(figuritaRepository.allInstances().map { it.jugador }.contains(jugador)){
            throw BusinessException("El jugador está asociado a una figurita, y no puede ser eliminado")
        } else {
            jugadorRepo.delete(jugador)
        }
    }

    /*fun deleteFigurita(id: Int) {
        val figurita = figuritaRepository.getById(id)
        if(usuarioRepository.allInstances().flatMap { usuario -> usuario.figuritasFaltantes}.contains(figurita)
            || usuarioRepository.allInstances().flatMap { usuario -> usuario.figuritasRepetidas}.contains(figurita)){
            throw BusinessException("La figurita pertenece a un usuario, y no puede ser eliminada ")
        } else {
            figuritaRepository.delete(figurita)
        }
    }*/
}