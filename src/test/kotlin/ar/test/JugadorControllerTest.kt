package ar.test

import Delantero
import Jugador
import Seleccion
import ar.edu.unsam.algo3.ProyectoApplication
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoJugador
import ar.edu.unsam.algo3.repository.RepoUser
import ar.edu.unsam.algo3.service.JugadorService
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

@ContextConfiguration(classes = [ProyectoApplication::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Dado un controller de Jugador")
class JugadorControllerTest(@Autowired val mockMvc: MockMvc) {

    //@Autowired
    //lateinit var jugadorRepo: RepoJugador

    @Test
    fun `Se puede traer la lista de todos los jugadores del repositorio`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/jugadores"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `Se puede traer la lista de todos los jugadores del repositorio según el campo de búsqueda`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/jugadores")
                .param("campoDeBusqueda", "enzo")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Enzo"))
    }

    @Test
    fun `No se puede traer un jugador que no exista a través del campo de búsqueda`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/jugadores")
                .param("campoDeBusqueda", "pepe")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json("[]"))
    }

    @Test
    fun `Se puede eliminar un jugador del repositorio`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/deleteJugador")
                .param("idJugador", "8")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

//    @Test
//    fun `No se puede eliminar un jugador del repositorio que está asociado a una figurita`() {
  //      val errorMessage = mockMvc.perform(
    //        MockMvcRequestBuilders.delete("/deleteJugador")
      //          .param("idJugador", "7")
        //)
          //  .andReturn().resolvedException?.message

      //  Assertions.assertEquals(errorMessage, "El jugador está asociado a una figurita, y no puede ser eliminado")
    //}

}