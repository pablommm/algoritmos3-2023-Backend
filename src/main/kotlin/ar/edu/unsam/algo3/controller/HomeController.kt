package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.HomeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("http://localhost:4200/")
class HomeController(@Autowired val homeService : HomeService) {

    @GetMapping("/Home/")
    fun getDatosHome() = homeService.getCantidadesHome()

}