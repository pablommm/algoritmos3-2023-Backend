package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.FiguritaService
import ar.edu.unsam.algo3.service.RepositorioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:4200/")
class RepositorioController(@Autowired val repositorioService : RepositorioService) {

    @GetMapping("/Home/")
    fun getFiguritas() = repositorioService.getCantidadesHome()

}