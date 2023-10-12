package ar.edu.unsam.algo3.controller

import Figurita
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class FigurinesController (@Autowired val figuritaService :FiguritaService ){

    @GetMapping("/Figures/")
    fun getFiguritas() = figuritaService.allInstance()

    @GetMapping("/Figurine/{id}")
    @Operation(summary = "Devuelve un punto de ventas buscado por id")
    fun getFigurines(@PathVariable id: Int) = figuritaService.getFigurita(id)



    @GetMapping("/FigurinesFiltrado/{nombreABuscar}")
    fun getFigurinesFiltrado(@PathVariable nombreABuscar : String) = figuritaService.getFiguritaFiltrado(nombreABuscar)

    @PostMapping("/nuevaFigurita")
    @Operation(summary = "Crea un nuevo punto de ventas")
    fun create(@RequestBody figuritaBody : Figurita): Figurita {
        return figuritaService.create(figuritaBody)
    }

    @PutMapping("/Figurine/{id}")
    @Operation(summary = "Actualiza puntoDeVentas")
    fun updateFigurine(@RequestBody figuritaBody: Figurita) {
        //if (true) throw BusinessException("Unknown error happened")
        return figuritaService.updateFigurita(figuritaBody)
    }

    @DeleteMapping("/Figurine/{id}")
    fun deleteFigurine(@PathVariable id : Int) = figuritaService.deleteFigurita(id)
}