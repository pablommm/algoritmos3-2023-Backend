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

}