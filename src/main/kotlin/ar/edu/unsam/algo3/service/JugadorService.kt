package ar.edu.unsam.algo3.service

import Arquero
import BusinessException
import Defensor
import Delantero
import Jugador
import Mediocampista
import Posicion
import ar.edu.unsam.algo3.dto.CreateJugadorDTO
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoJugador
import ar.edu.unsam.algo3.repository.RepoSeleccion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class JugadorService {

    @Autowired
    lateinit var jugadorRepo: RepoJugador

    @Autowired
    lateinit var repoSeleccion: RepoSeleccion


    @Autowired
    lateinit var figuritaRepository: RepoFigurita

    fun getJugadores(campoDeBusqueda: String?) :List<Jugador> = jugadorRepo.searchByName(campoDeBusqueda)
    fun getJugadores(id: Int) = jugadorRepo.getById(id)

    fun getJugadoresDTO() :List<JugadorDTO> = jugadorRepo.allInstances().map { it.toDTO() }

    fun create(jugadorDTO: CreateJugadorDTO) {

        val jugador = Jugador(
            nombre = jugadorDTO.nombre,
            apellido = jugadorDTO.apellido,
            fechaDeNacimiento = LocalDate.parse(jugadorDTO.fechaDeNacimiento),
            nroDeCamiseta = jugadorDTO.nroDeCamiseta,
            altura = jugadorDTO.altura,
            peso = jugadorDTO.peso,
            lider = jugadorDTO.lider,
            anioDebut = LocalDate.parse(jugadorDTO.anioDebut),
            posicion = definirPosicion(jugadorDTO.posicion),
            pais = jugadorDTO.pais,
            cotizacion = jugadorDTO.cotizacion,
            seleccion = repoSeleccion.getById(jugadorDTO.idSeleccion)
        )
        jugadorRepo.create(jugador)
    }

    fun definirPosicion(posicion: String): Posicion {
        val posicionUpper = posicion.uppercase()
        if(posicionUpper == "DELANTERO"){
            return Delantero
        } else if(posicionUpper == "MEDIOCAMPISTA"){
            return Mediocampista
        } else if (posicionUpper == "DEFENSOR"){
            return Defensor
        } else {
            return Arquero
        }
    }

    fun deleteJugador(id: Int) {
        val jugador = jugadorRepo.getById(id)
        if(figuritaRepository.allInstances().map { it.jugador }.contains(jugador)){
            throw BusinessException("El jugador está asociado a una figurita, y no puede ser eliminado")
        } else {
            jugadorRepo.delete(jugador)
        }
    }
}