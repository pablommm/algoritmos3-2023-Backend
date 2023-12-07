package ar.test

import ar.edu.unsam.algo3.ProyectoApplication
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
class DireccionControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `Se puede el objeto de las direcciones`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/getDireccion"))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}