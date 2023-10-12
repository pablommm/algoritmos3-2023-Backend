package ar.edu.unsam.algo3.controller

import Jugador
import PuntoDeVentas
import ar.edu.unsam.algo3.service.JugadorService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class JugadorController(@Autowired val jugadorService: JugadorService){


    @GetMapping("/jugador/")
    fun getJugador() = jugadorService.getJugadores()

    @GetMapping("/jugador/{id}")
    @Operation(summary = "Devuelve un jugador buscado por id")
    fun getJugador(@PathVariable id: Int) = jugadorService.getJugador(id)

    @PostMapping("/nuevoJugador/")
    @Operation(summary = "Crea un nuevo jugador")
    fun create(@RequestBody jugadorBody: Jugador): Jugador {
        return jugadorService.create(jugadorBody)
    }

    @GetMapping("/jugadorFiltrado/{nombreABuscar}")
    fun getJugadorFiltrado(@PathVariable nombreABuscar : String) = jugadorService.getJugadorFiltrado(nombreABuscar)


    @PutMapping("/modificarJugador/{id}")
    @Operation(summary = "Actualiza jugador")
    fun updateJugador(@RequestBody jugadorBody: Jugador) {
        //if (true) throw BusinessException("Unknown error happened")
        return jugadorService.updateJugador(jugadorBody)
    }

    @DeleteMapping("/borrarJugador/{id}")
    fun deleteJugador(@PathVariable id : Int) = jugadorService.deleteJugador(id)


}