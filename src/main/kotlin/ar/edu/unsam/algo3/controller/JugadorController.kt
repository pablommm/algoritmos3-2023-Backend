package ar.edu.unsam.algo3.controller



import Jugador
import ar.edu.unsam.algo3.dto.CreateFiguritaDTO
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
    fun getJugador(@PathVariable id: Int) = jugadorService.getJugador(id)

    @GetMapping("/jugadores/createFigurita")
    fun getJugador() = jugadorService.getJugadoresDTO()

    @PostMapping("/crearJugador")
    @Operation(summary = "Crea un nuevo jugador")
    fun create(@RequestBody jugador: CreateJugadorDTO) = jugadorService.create(jugador)

    @DeleteMapping("/deleteJugador")
    fun deletePuntoDeVentas (@RequestParam("idJugador") idJugador: Int) = jugadorService.deleteJugador(idJugador)

    @GetMapping("/editarJugador/{idJugador}")
    @Operation(summary = "Sirve para traer los datos del jugador a editar en el frontend")
    fun getById(@PathVariable idJugador : Int) = jugadorService.getJugador(idJugador)

    @PutMapping("/updateJugador")
    @Operation(summary = "Edita el jugador en el backend")
    fun update(@RequestBody jugador: CreateJugadorDTO) = jugadorService.update(jugador)

}