package ar.edu.unsam.algo3.controller;

import ar.edu.unsam.algo3.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController(@Autowired val userService : UsuarioService) {
    @GetMapping("/Usuario/")
    fun getUsuario() = userService.getUser()


}
