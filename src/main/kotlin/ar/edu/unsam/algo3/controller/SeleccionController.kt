package ar.edu.unsam.algo3.controller

import Seleccion
import ar.edu.unsam.algo3.service.SeleccionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin("*")
class SeleccionController( @Autowired val seleccionService: SeleccionService
) {

    @GetMapping("/Selecciones")
    fun getSeleccion(@RequestParam campoDeBusqueda: String? = "") = seleccionService.getSelecciones(campoDeBusqueda)

    @GetMapping("/Seleccion/{id}")
    @Operation(summary = "Devuelve una seleccion buscado por id")
    fun getSeleccion(@PathVariable id: Int) = seleccionService.getSeleccion(id)

    @PostMapping("/nuevaSeleccion")
    @Operation(summary = "Crea una nueva seleccion")
    fun create(@RequestBody seleccionBody: Seleccion): Seleccion {
        return seleccionService.create(seleccionBody)
    }

    @GetMapping("/seleccionesFiltrado/{nombreABuscar}")
    fun getSeleccionesFiltrado(@PathVariable nombreABuscar: String) = seleccionService.getSeleccionesFiltrado(nombreABuscar)

    @PutMapping("/Seleccion/{id}")
    @Operation(summary = "actualizar Seleccion")
    fun updateSeleccion(@RequestBody seleccionBody: Seleccion){
        return seleccionService.updateSeleccion(seleccionBody)
    }

    @DeleteMapping("/deleteSeleccion")
    fun deletePuntoDeVentas (@RequestParam("idSeleccion") idSeleccion: Int) = seleccionService.deleteSeleccion(idSeleccion)

    @GetMapping("/confederaciones")
    fun getConfederaciones() = seleccionService.getConfederaciones()

    //fun seleccionesGet() = service.allInstance()

}