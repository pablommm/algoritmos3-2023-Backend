package ar.edu.unsam.algo3.controller



import Jugador
import ar.edu.unsam.algo3.dto.CreateJugadorDTO
import ar.edu.unsam.algo3.service.JugadorService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class JugadorController(@Autowired val jugadorService: JugadorService) {

    @GetMapping("/jugadores")
    fun getJugador(@RequestParam campoDeBusqueda: String? = "") = jugadorService.getJugadores(campoDeBusqueda)

    @GetMapping("/jugadores/{id}")
    fun getJugador(id: Int) = jugadorService.getJugadores(id)

    @GetMapping("/jugadores/createFigurita")
    fun getJugador() = jugadorService.getJugadoresDTO()

    @PostMapping("/crearJugador")
    @Operation(summary = "Crea un nuevo jugador")
    fun create(@RequestBody jugador: CreateJugadorDTO) = jugadorService.create(jugador)

    @DeleteMapping("/deleteJugador")
    fun deletePuntoDeVentas (@RequestParam("idJugador") idJugador: Int) = jugadorService.deleteJugador(idJugador)

}