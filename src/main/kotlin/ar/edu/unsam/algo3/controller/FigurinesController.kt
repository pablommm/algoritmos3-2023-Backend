package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class FigurinesController (@Autowired val figuritaService :FiguritaService ){

    @GetMapping("/Figures/")
    fun getFiguritas() = figuritaService.allInstance()
/*
    @GetMapping("/Figurine/{id}")
    @Operation(summary = "Devuelve un punto de ventas buscado por id")
    fun getFigurines(@PathVariable id: Int) = figuritaService.getFigurita(id)



    @GetMapping("/FigurinesFiltrado/{nombreABuscar}")
    fun getFigurinesFiltrado(@PathVariable nombreABuscar : String) = figuritaService.getFiguritaFiltrado(nombreABuscar)

*/
}