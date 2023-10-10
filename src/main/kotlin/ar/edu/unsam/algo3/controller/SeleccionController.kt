package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.SeleccionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SeleccionController( @Autowired val service: SeleccionService
) {

    @GetMapping("/Selecciones/")
    fun seleccionesGet() = service.allInstance()

}