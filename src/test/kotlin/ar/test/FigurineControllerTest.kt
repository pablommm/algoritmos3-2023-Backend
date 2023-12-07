package ar.test

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
class FigurineControllerTest (@Autowired val mockMvc: MockMvc){
    //@Autowired
    //lateinit var repoFigurita: RepoFigurita

    @Test
    fun `Se puede traer la lista de todas las figuritas del repositorio`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/TodasLasFiguritas/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `Se puede traer la lista de todas las figuritas del repositorio según el campo de búsqueda`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/TodasLasFiguritas/")
            .param("campoDeBusqueda", "enzo"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Enzo"))
    }

    @Test
    fun `No se puede traer una figurita que no exista a través del campo de búsqueda`() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/TodasLasFiguritas/")
            .param("campoDeBusqueda", "pepe"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json("[]"))
    }

    @Test
    fun `Se puede eliminar una figurita del repositorio`(){
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/deleteFigurita")
            .param("idFigurita", "7"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `No se puede eliminar una figurita del repositorio que está asociada a un usuario`(){
        val errorMessage = mockMvc.perform(
            MockMvcRequestBuilders.delete("/deleteFigurita")
            .param("idFigurita", "6"))
            .andReturn().resolvedException?.message

        Assertions.assertEquals(errorMessage,"La figurita pertenece a un usuario, y no puede ser eliminada")
    }
}