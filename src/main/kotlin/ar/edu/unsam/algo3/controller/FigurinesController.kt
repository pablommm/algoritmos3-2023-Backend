package ar.edu.unsam.algo3.controller

import Figurita
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ar.edu.unsam.algo3.dto.toDTO

@RestController
@CrossOrigin("http://localhost:4200/")
class FigurinesController (@Autowired val figuritaService :FiguritaService ){

    @GetMapping("/BusquedaFiguritasPerfil/")
    fun getFiguritas() = figuritaService.getFigurines()

    @GetMapping("/FiguritasRepetidas/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas repetidas menos las del usuario logueado")
    fun getFiguritasRepetidas(@PathVariable idUsuario : Int,
                              @RequestParam desde: Int? = 0,
                              @RequestParam hasta: Int? = 0,
                              @RequestParam esPromesa: Boolean? = false,
                              @RequestParam esOnFire: Boolean? = false)
    = figuritaService.getFiguritasRepetidas(idUsuario, desde, hasta, esPromesa, esOnFire)

    @GetMapping("/DetalleFigurita/{idFigurita}")
    @Operation(summary = "Trae la figurita solicitada a partir del id, utilizada para DetalleFigurita")
    fun getFiguritaById(@PathVariable idFigurita : Int) = figuritaService.getFiguritasById(idFigurita)


    @GetMapping("/PerfilUsuario/FiguritasFaltantes/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas faltantes del usuario logueado")
    fun getFiguritasFaltantesUsuario(@PathVariable idUsuario : Int) = figuritaService.getFiguritasFaltantesUsuario(idUsuario)

    @GetMapping("/PerfilUsuario/FiguritasRepetidas/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas repetidas del usuario logueado")
    fun getFiguritasRepetidasUsuario(@PathVariable idUsuario : Int) = figuritaService.getFiguritasRepetidasUsuario(idUsuario)

}