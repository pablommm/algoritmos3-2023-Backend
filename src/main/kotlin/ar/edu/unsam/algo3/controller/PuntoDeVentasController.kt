package ar.edu.unsam.algo3.controller

import PuntoDeVentas
import ar.edu.unsam.algo3.dto.PuntosDeVentaDTOUserless
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

//    @GetMapping("/puntoDeVentas/")
//    fun getAllPuntoDeVentas(@RequestParam campoDeBusqueda: String? = "")
//            = puntoDeVentasService.getPuntosDeVenta(campoDeBusqueda)

    @GetMapping("/puntoDeVentas/")
    fun getAllPuntoDeVentas(@RequestParam campoDeBusqueda: String? = "")
            = puntoDeVentasService.getPuntosDeVenta()

    @GetMapping("/puntoDeVentas2/")
    fun getAllPuntoDeVentas2()
            = puntoDeVentasService.getPuntosDeVenta()


    @PostMapping("/nuevoPuntoDeVentas/")
    @Operation(summary = "Crea un nuevo punto de ventas")
    fun create(@RequestBody puntoDeVentasBody : PuntoDeVentas) {
        return puntoDeVentasService.create(puntoDeVentas = puntoDeVentasBody)
    }

//    @PostMapping("/nuevoPuntoDeVentas/")
//    @Operation(summary = "Crea un nuevo punto de ventas")
//    fun create(@RequestBody puntoDeVentas : PuntosDeVentaDTOUserless) = puntoDeVentasService.create(puntoDeVentas)

//    @PostMapping("/nuevoPuntoDeVentas2/")
//    @Operation(summary = "Crea un nuevo punto de ventas")
//    fun create2(@RequestBody puntoVentas : puntoDeVentas ) = puntoDeVentasService.create(puntoDeVentas)

    @DeleteMapping("/deletePuntoDeVentas")
    fun deletePuntoDeVentas (@RequestParam("idPuntoDeVentas") idPuntoDeVentas: Int) = puntoDeVentasService.deletePuntoDeVenta(idPuntoDeVentas)

    @GetMapping("/editarPuntoDeVentas/{idPuntoDeVenta}")
    @Operation(summary = "Sirve para traer los datos del PuntoDeVentas a editar en el frontend")
    fun getById(@PathVariable idPuntoDeVenta: Int) = puntoDeVentasService.getUnPuntoDeVentas(idPuntoDeVenta)

    //@GetMapping("/puntoDeVentas/")
    //fun getPuntoDeVentas() = puntoDeVentasService.getPuntoDeVentasFiltrado(null)
    //fun getPuntoDeVentas() = puntoDeVentasService.puntoDeVentasRepository.allInstances()

//    @PutMapping("/editarPuntoDeVentas/{id}")
//    @Operation(summary = "Sirve para editar un PuntoDeVentas")
//    fun update(@RequestBody puntoDeVentas : PuntoDeVentas) = puntoDeVentasService.update(puntoDeVentas)


    @PutMapping("/editarPuntoDeVentas")
    @Operation(summary = "Sirve para editar un PuntoDeVentas")
    fun update(@RequestBody puntoDeVentas : PuntoDeVentas) = puntoDeVentasService.update(puntoDeVentas)




}