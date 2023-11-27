package ar.edu.unsam.algo3.controller

import Figurita
import ar.edu.unsam.algo3.dto.FiguritaUsuarioDTO
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ar.edu.unsam.algo3.dto.toDTO

@RestController
@CrossOrigin("*")
class FigurinesController (@Autowired val figuritaService :FiguritaService ){

    @GetMapping("/BusquedaFiguritasPerfil/")
    fun getFiguritas(@RequestParam desde: Int? = 0,
                     @RequestParam hasta: Int? = 0,
                     @RequestParam esPromesa: Boolean? = false,
                     @RequestParam esOnFire: Boolean? = false) = figuritaService.getFigurines()

    @GetMapping("/TodasLasFiguritas/")
    fun getAllFiguritas(@RequestParam campoDeBusqueda: String? = "") = figuritaService.getAllFiguritas(campoDeBusqueda)

    @GetMapping("/FiguritasRepetidas/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas repetidas menos las del usuario logueado")
    fun getFiguritasRepetidas(@PathVariable idUsuario : Int,
                              @RequestParam campoDeBusqueda: String? = "",
                              @RequestParam desde: Int? = 0,
                              @RequestParam hasta: Int? = 0,
                              @RequestParam esPromesa: Boolean? = false,
                              @RequestParam esOnFire: Boolean? = false)
    = figuritaService.getFiguritasRepetidas(idUsuario,campoDeBusqueda, desde, hasta, esPromesa, esOnFire)

    @GetMapping("/DetalleFigurita/{idFigurita}")
    @Operation(summary = "Trae la figurita solicitada a partir del id, utilizada para DetalleFigurita")
    fun getFiguritaById(@PathVariable idFigurita : Int) = figuritaService.getFiguritasById(idFigurita)


    @GetMapping("/PerfilUsuario/FiguritasFaltantes/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas faltantes del usuario logueado")
    fun getFiguritasFaltantesUsuario(@PathVariable idUsuario : Int) = figuritaService.getFiguritasFaltantesUsuario(idUsuario)

    @GetMapping("/PerfilUsuario/FiguritasRepetidas/{idUsuario}")
    @Operation(summary = "Trae todas las figuritas repetidas del usuario logueado")
    fun getFiguritasRepetidasUsuario(@PathVariable idUsuario : Int) = figuritaService.getFiguritasRepetidasUsuario(idUsuario)

    @PostMapping("/DetallesFigurita/{idUsuarioLogueado}")
    fun solicitarFigurita(@PathVariable idUsuarioLogueado: Int,
                            @RequestBody usuarioFigurita: FiguritaUsuarioDTO) = figuritaService.solicitarFigurita(idUsuarioLogueado, usuarioFigurita)

    @PostMapping("/PerfilUsuario/FiguritasRepetidas/QuitarFigurita/{idUsuarioLogueado}")
    fun quitarFiguritaRepetida(@PathVariable idUsuarioLogueado: Int,
                            @RequestBody idFigurita: Int) = figuritaService.quitarFiguritaRepetida(idUsuarioLogueado, idFigurita)

    @PostMapping("/PerfilUsuario/FiguritasFaltantes/QuitarFigurita/{idUsuarioLogueado}")
    fun quitarFiguritaFaltante(@PathVariable idUsuarioLogueado: Int,
                               @RequestBody idFigurita: Int) = figuritaService.quitarFiguritaFaltante(idUsuarioLogueado, idFigurita)
}