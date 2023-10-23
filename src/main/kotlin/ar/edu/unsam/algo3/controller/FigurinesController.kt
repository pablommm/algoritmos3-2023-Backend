package ar.edu.unsam.algo3.controller

import Figurita
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class FigurinesController (@Autowired val figuritaService :FiguritaService ){

    @GetMapping("/FiguritasRepetidas/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas repetidas menos las del usuario logueado")
    fun getFiguritasRepetidas(@PathVariable idUsuario : Int) = figuritaService.getFiguritasRepetidas(idUsuario)

    @GetMapping("/PerfilUsuario/FiguritasFaltantes/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas faltantes del usuario logueado")
    fun getFiguritasFaltantesUsuario(@PathVariable idUsuario : Int) = figuritaService.getFiguritasFaltantesUsuario(idUsuario)

    @GetMapping("/PerfilUsuario/FiguritasRepetidas/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas repetidas del usuario logueado")
    fun getFiguritasRepetidasUsuario(@PathVariable idUsuario : Int) = figuritaService.getFiguritasRepetidasUsuario(idUsuario)

}