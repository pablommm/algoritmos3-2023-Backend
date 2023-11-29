package ar.edu.unsam.algo3.controller

import PuntoDeVentas
import ar.edu.unsam.algo3.service.CriterioOrdenamiento
import ar.edu.unsam.algo3.service.PuntoDeVentasService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class PuntoDeVentasController(@Autowired val puntoDeVentasService: PuntoDeVentasService) {

    @GetMapping("/puntoDeVentas/{idUsuario}")
    fun getPuntoDeVentas( @PathVariable idUsuario: Int,
                          @RequestParam("campoDeBusqueda") campoDeBusqueda: String?,
                         @RequestParam("criterioOrdenamiento") criterioOrdenamiento: CriterioOrdenamiento?)
    = puntoDeVentasService.getPuntoDeVentasFiltrado(campoDeBusqueda, criterioOrdenamiento, idUsuario)

    @GetMapping("/puntoDeVentas/")
    fun getAllPuntoDeVentas(@RequestParam campoDeBusqueda: String? = "")
            = puntoDeVentasService.getPuntosDeVenta(campoDeBusqueda)

//    @PostMapping("/puntoDeVentas/")
//    @Operation(summary = "Crea un nuevo punto de ventas")
//    fun create(@RequestBody puntoDeVentasBody : PuntoDeVentas): PuntoDeVentas {
//        return puntoDeVentasService.create(puntoDeVentasBody)
//    }

    @PostMapping("/nuevoPuntoDeVentas/")
    @Operation(summary = "Crea un nuevo punto de ventas")
    fun create(@RequestBody puntoDeVentasBody : PuntoDeVentas) : PuntoDeVentas {
        return puntoDeVentasService.create(puntoDeVentasBody)
    }

    @DeleteMapping("/deletePuntoDeVentas")
    fun deletePuntoDeVentas (@RequestParam("idPuntoDeVentas") idPuntoDeVentas: Int) = puntoDeVentasService.deletePuntoDeVenta(idPuntoDeVentas)

    //@GetMapping("/puntoDeVentas/")
    //fun getPuntoDeVentas() = puntoDeVentasService.getPuntoDeVentasFiltrado(null)
    //fun getPuntoDeVentas() = puntoDeVentasService.puntoDeVentasRepository.allInstances()

}