package ar.edu.unsam.algo3.controller;

import Usuario
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
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

    @PostMapping("/NuevoUsuario")
    @Operation(summary = "Crea un nuevo usuario")
    fun create(@RequestBody usuarioBody : Usuario): Usuario {
        return userService.create(usuarioBody)
    }
    @PostMapping("/usuarioLogin")
    @Operation(summary = "Devuelve un usuario que coincida user y pass")
    fun postUsuarioLoggin(@RequestBody user:UsuarioLoginDTO) = userService.getUsuarioLogin(user)

    @GetMapping("/UsuarioFiltrado/{nombreABuscar}")
    fun getUsuarioFiltrado(@PathVariable nombreABuscar : String) = userService.getUserFiltrado(nombreABuscar)


    @PutMapping("/Usuario/{id}")
    @Operation(summary = "Actualiza usuario")
    fun updateUsuario(@RequestBody usuarioBody: Usuario) {
        //if (true) throw BusinessException("Unknown error happened")
        return userService.updateUser(usuarioBody)
    }

    @DeleteMapping("/Usuario/{id}")
    fun deleteUsuario(@PathVariable id : Int) = userService.deleteUser(id)

    //@GetMapping("/listaTiposUsuarios")
    //fun getlistaTiposUsuarios()  =  userService.getListaTiposUsuarios()


}
