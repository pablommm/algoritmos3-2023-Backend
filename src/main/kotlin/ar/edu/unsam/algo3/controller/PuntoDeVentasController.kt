package ar.edu.unsam.algo3.controller

import PuntoDeVentas
import ar.edu.unsam.algo3.service.PuntoDeVentasService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class PuntoDeVentasController(@Autowired val puntoDeVentasService: PuntoDeVentasService) {


    @GetMapping("/puntoDeVentas/")
    fun getPuntoDeVentas() = puntoDeVentasService.getPuntoDeVentas()

    @GetMapping("/puntoDeVenta/{id}")
    @Operation(summary = "Devuelve un punto de ventas buscado por id")
    fun getPuntoDeVenta(@PathVariable id: Int) = puntoDeVentasService.getPuntoDeVenta(id)

    @PostMapping("/nuevoPuntoDeVentas")
    @Operation(summary = "Crea un nuevo punto de ventas")
    fun create(@RequestBody puntoDeVentasBody : PuntoDeVentas): PuntoDeVentas {
        return puntoDeVentasService.create(puntoDeVentasBody)
    }

    @GetMapping("/puntoDeVentasFiltrado/{nombreABuscar}")
    fun getPuntoDeVentasFiltrado(@PathVariable nombreABuscar : String) = puntoDeVentasService.getPuntoDeVentasFiltrado(nombreABuscar)


    @PutMapping("/puntoDeVenta/{id}")
    @Operation(summary = "Actualiza puntoDeVentas")
    fun updatePuntoDeventas(@RequestBody puntoDeVentasBody: PuntoDeVentas) {
        //if (true) throw BusinessException("Unknown error happened")
        return puntoDeVentasService.updatePuntoDeVenta(puntoDeVentasBody)
    }

    @DeleteMapping("/puntoDeVenta/{id}")
    fun deletePuntoDeVenta(@PathVariable id : Int) = puntoDeVentasService.deletePuntoDeVenta(id)

}