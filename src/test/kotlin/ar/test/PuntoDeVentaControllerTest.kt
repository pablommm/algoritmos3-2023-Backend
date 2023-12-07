package ar.test

import PuntoDeVentas
import ar.edu.unsam.algo3.ProyectoApplication
import ar.edu.unsam.algo3.service.CriterioOrdenamiento
import ar.edu.unsam.algo3.service.PuntoDeVentasService
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

@ContextConfiguration(classes = [ProyectoApplication::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Dado un controller de PuntoDeVentas")
class PuntoDeVentasControllerTest(@Autowired val mockMvc: MockMvc) {

    //@Autowired
    //lateinit var puntoDeVentasRepo: RepoPuntoDeVentas

    @Test
    fun `Se puede traer la lista de todos los puntos de ventas del repositorio`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/puntoDeVentas/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


    @Test
    fun `Se puede eliminar un punto de ventas del repositorio`(){
        mockMvc.perform(MockMvcRequestBuilders.delete("/deletePuntoDeVentas")
            .param("idPuntoDeVentas", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `total de todos los puntos de ventas del repositorio`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/puntoDeVentas2/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(4))
    }


    @Test
    fun `Se puede traer un punto de ventas del repositorio`(){
        mockMvc.perform(MockMvcRequestBuilders.get("/editarPuntoDeVentas/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `Se puede traer un punto de ventas del repositorio por id`(){
        mockMvc.perform(MockMvcRequestBuilders.get("/puntoDeVentas/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `Se puede traer un punto de ventas del repositorio por nombre`(){
        mockMvc.perform(MockMvcRequestBuilders.get("/puntoDeVentas/1"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `No se puede validar url template o punto de venta con ID no existente `(){
        mockMvc.perform(MockMvcRequestBuilders.get("/puntoDeVentas/99"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}
