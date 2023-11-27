package ar.edu.unsam.algo3.controller



import ar.edu.unsam.algo3.service.JugadorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
@RestController
@CrossOrigin("*")
class JugadorController(@Autowired val jugadorService: JugadorService) {

    @GetMapping("/jugadores")
    fun getJugador(@RequestParam campoDeBusqueda: String? = "") = jugadorService.getJugadores(campoDeBusqueda)

    @GetMapping("/jugadores/{id}")
    fun getJugador(id: Int) = jugadorService.getJugadores(id)



}