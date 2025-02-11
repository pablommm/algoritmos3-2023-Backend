package ar.test


import Seleccion
import ar.edu.unsam.algo3.ProyectoApplication
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
@DisplayName("Dado un controller de Figurita")
class SeleccionControllerTest (@Autowired val mockMvc: MockMvc){

    @Test
    fun `Se puede traer la lista de todas las selecciones`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/Selecciones"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


    @Test
    fun `Se puede obtener el nombre de la selección por ID`() {
        val idSeleccion = 1
        val expectedNombre = "Argentina"

        mockMvc.perform(MockMvcRequestBuilders.get("/Seleccion/{id}", idSeleccion))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.pais").value(expectedNombre))

    }

    @Test
    fun `Se puede traer la lista de todas las confederaciones`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/confederaciones"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


    @Test
    fun `No se puede traer una selección que no exista a través del campo de búsqueda`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/Selecciones")
                .param("campoDeBusqueda", "seleccionInexistente")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json("[]"))
    }

    @Test
    fun `Se puede eliminar una selección del repositorio`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/deleteSeleccion")
                .param("idSeleccion", "2")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


    @Test
    fun `Se puede traer una selección del repositorio a través del campo de búsqueda`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/Selecciones")
                .param("campoDeBusqueda", "Argentina")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
    }

    @Test
    fun `No se puede eliminar una seleccion que no existe`() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/deleteSeleccion")
                .param("idSeleccion", "200")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
    @Test
    fun `No se puede eliminar una seleccion boca `() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/deleteSeleccion")
                .param("pais", "Boca")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

}




