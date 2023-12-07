package ar.test
import ar.edu.unsam.algo3.ProyectoApplication
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
@DisplayName("Dado un controller de Seleccion")
class SeleccionControllerTest(@Autowired val mockMvc: MockMvc) {

    //@Autowired
    //lateinit var seleccionRepo: RepoSeleccion

    @Test
    fun `Se puede traer la lista de todas las selecciones del repositorio`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/Selecciones"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }



    @Test
    fun `No se puede traer una selección que no exista a través del campo de búsqueda`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/Selecciones")
            .param("campoDeBusqueda", "seleccionInexistente"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json("[]"))
    }

    @Test
    fun `Se puede eliminar una selección del repositorio`(){
        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteSeleccion")
            .param("idSeleccion", "2"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }



}
