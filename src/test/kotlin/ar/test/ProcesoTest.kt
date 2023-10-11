import edu.Repositorio
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import java.time.LocalDate

class ProcesoTests : DescribeSpec( {
    isolationMode = IsolationMode.InstancePerTest
    val figuritasFaltantes = mutableListOf<Figurita>()
    val figuritasRepetidas = mutableListOf<Figurita>()
    val DireccionSanMartin = Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val seleccionArgentina = Seleccion("argentina", Confederacion.CONCACAF, 13, 7)
    val jugadorRiquelme = Jugador(
        "Roman",
        "Riquelme",
        LocalDate.now().minusYears(75),
        10,
        seleccionArgentina,
        LocalDate.now().minusYears(10),
        1.80,
        150.0,
        Mediocampista,
        lider = true,
        "ARG"
    )
    val usuarioMayorEdad = Usuario(
        name = "Jose", apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        figuritasFaltantes,
        figuritasRepetidas,
        Desprendido,
        jugadorRiquelme,
        20
    )
    val usuarioMenorEdad = Usuario(
        name = "Maria",
        apellido = "Inez",
        username = "MariaInez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        figuritasFaltantes,
        figuritasRepetidas,
        Desprendido,
        jugadorRiquelme,
        20
    )
    val procesoAdmin = ProcesoDeAdministracion()


    describe("Proceso") {
        it("Prueba de proceso") {
            //val repoUsuario = Repositorio<Entidad>()
            val repoUsuario = Repositorio<Usuario>()
            val listaUsuariosInactivos = listOf(usuarioMayorEdad)
            val proceso = BorrarUsuarioInactivo(repoUsuario)
            //proceso.execute()
            val listaProcesos = listOf(proceso)
            //procesoAdmin.run(listaProcesos, repoUsuario )
        }
    describe("Proceso de administracion") {

        }
    }
})