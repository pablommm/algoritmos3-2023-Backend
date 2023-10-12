package ar.edu.unsam.algo3.controller;

import Usuario
import ar.edu.unsam.algo3.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*

@RestController
class UserController(@Autowired val userService : UsuarioService) {
    @GetMapping("/Usuario/")
    fun getUsuario() = userService.getUser()

    @GetMapping("/Usuario/{id}")
    @Operation(summary = "Devuelve un usuario por id")
    fun getUsuario(@PathVariable id: Int) = userService.getUser(id)

    @PostMapping("/nuevoPuntoDeVentas")
    @Operation(summary = "Crea un nuevo usuario")
    fun create(@RequestBody usuarioBody : Usuario): Usuario {
        return userService.create(usuarioBody)
    }

    @GetMapping("/puntoDeVentasFiltrado/{nombreABuscar}")
    fun getUsuarioFiltrado(@PathVariable nombreABuscar : String) = userService.getUserFiltrado(nombreABuscar)


    @PutMapping("/puntoDeVenta/{id}")
    @Operation(summary = "Actualiza usuario")
    fun updateUsuario(@RequestBody usuarioBody: Usuario) {
        //if (true) throw BusinessException("Unknown error happened")
        return userService.updateUser(usuarioBody)
    }

    @DeleteMapping("/puntoDeVenta/{id}")
    fun deleteUsuario(@PathVariable id : Int) = userService.deleteUser(id)

}
