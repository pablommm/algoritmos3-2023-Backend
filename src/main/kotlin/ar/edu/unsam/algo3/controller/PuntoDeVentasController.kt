package ar.edu.unsam.algo3.controller

import PuntoDeVentas
import ar.edu.unsam.algo3.service.PuntoDeVentasService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("http://localhost:4200/")
class PuntoDeVentasController(@Autowired val puntoDeVentasService: PuntoDeVentasService) {

    @GetMapping("/puntoDeVentas/")
    fun getPuntoDeVentas(@RequestParam("campoDeBusqueda") campoDeBusqueda: String? = "",
                         @RequestParam("criterioOrdenamiento") criterioOrdenamiento: String? = "")
    = puntoDeVentasService.getPuntoDeVentasFiltrado(campoDeBusqueda, criterioOrdenamiento)

    //@GetMapping("/puntoDeVentas/")
    //fun getPuntoDeVentas() = puntoDeVentasService.getPuntoDeVentasFiltrado(null)
    //fun getPuntoDeVentas() = puntoDeVentasService.puntoDeVentasRepository.allInstances()

}