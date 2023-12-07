package ar.test
import Delantero
import Desprendido
import Direccion
import Figurita
import Jugador
import Seleccion
import Usuario
import ar.edu.unsam.algo3.ProyectoApplication
import ar.edu.unsam.algo3.dto.CreateFiguritaDTO
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoJugador
import ar.edu.unsam.algo3.repository.RepoUser
import ar.edu.unsam.algo3.service.FiguritaService
import org.hibernate.validator.internal.util.Contracts.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDate

@ContextConfiguration(classes = [ProyectoApplication::class])
@SpringBootTest
class FiguritaServiceTest : InitializingBean {

    @InjectMocks
    lateinit var figuritaService: FiguritaService

    @Mock
    lateinit var figuritaRepository: RepoFigurita

    @Mock
    lateinit var jugadorRepo: RepoJugador

    @Mock
    lateinit var usuarioRepository: RepoUser


    final val argentina = Seleccion("Argentina",Confederacion.CONCACAF,3,2)
    val DireccionSanMartin =
        Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)

    val figuritasFaltantes = mutableListOf<Figurita>()

    val figuritasRepetidas = mutableListOf<Figurita>()



    val jugadorLeyenda =  Jugador("Leo","Messi",LocalDate.now().minusYears(30),10,argentina,LocalDate.now().minusYears(14),187.0,83.0, Delantero,true,"Argentina",21000000)

    val jugador2 =  Jugador("Enzo","Fernandez",LocalDate.now().minusYears(30),10,argentina,LocalDate.now().minusYears(14),187.0,83.0, Delantero,true,"Argentina",21000000)

    val figuritaPromesa =Figurita(1, nivelDeImpresion = NivelDeImpresion.BAJA,false,jugadorLeyenda, imagen = "https://shorturl.at/fhpG6")

    val figuritaDTO = CreateFiguritaDTO(1,1,1,true, NivelDeImpresion.BAJA, "https://shorturl.at/fhpG6")


    val usuarioPrueba = Usuario(
        name = "Jose",
        apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        figuritasFaltantes,
        figuritasRepetidas,
        Desprendido,
        jugadorLeyenda,
        20,
        password = "1234"
    )

    override fun afterPropertiesSet() {
        jugadorRepo.create(jugadorLeyenda)
        jugadorRepo.create(jugador2)
        figuritasFaltantes.add(figuritaPromesa)
        figuritaRepository.create(figuritaPromesa)
        usuarioRepository.create(usuarioPrueba)
    }

    @Test
    fun testGetAllFiguritas() {
        `when`(figuritaService.getFigurines()).thenReturn(listOf(figuritaPromesa).toMutableList())
    }

    @Test
    fun buscarFiguritaPorNombre(){
        `when`(figuritaService.getAllFiguritas("Leo")).thenReturn(listOf(figuritaPromesa).toMutableList())
    }

    @Test
    fun updateFigurita(){
        figuritaService.update(figuritaDTO)
        `when`(figuritaService.getAllFiguritas("Enzo")).thenReturn(listOf(figuritaPromesa).toMutableList())
    }

    @Test
    fun deleteFigurita(){
        figuritaService.deleteFigurita(1)
        `when`(figuritaService.getFigurines()).thenReturn(null)
    }




}
