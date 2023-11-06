package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.DireccionService

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:4200/")
class DirecionController {


    /*@GetMapping("/getProvincias")
    @Operation(summary = "Devuelve lista de provincias")
    fun getPronvinciasControler()  = DireccionService.getProvinciasService()
    */

/*
    @GetMapping("/getLocalidades/{provincia}")
    @Operation(summary = "Devuelve lista de localidades")
    fun getLocalidadesControler(@PathVariable prov: String)  = DireccionService.getLocalidadesService(prov)
*/
    @GetMapping("/getDireccion")
    @Operation(summary = "Devuelve todo el objeto")
    fun getDireccionControler()  = DireccionService


}