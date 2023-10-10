import io.kotest.assertions.any
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.*
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.LocalDate

class ObserverTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val listaDeAccion = mutableListOf<SolicitudObserver>()
    val seleccionEU = Seleccion("Estados Unidos", Confederacion.CONCACAF, 2, 3)

    val listaDerepetidasReservadas = mutableListOf<Figurita>()
    val DireccionSanMartin =
        Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val figuritasFaltantes = mutableListOf<Figurita>()
    val figuritasRepetidas = mutableListOf<Figurita>()
    val usuarioMayorEdad = Usuario(
        name = "Jose",
        apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        mutableListOf<Figurita>(),
        mutableListOf<Figurita>(),
        Desprendido,
        kmCercania = 3
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
        kmCercania = 3
    )
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
    val jugadorAlberto = Jugador(
        "Alberto",
        "peter",
        LocalDate.now().minusYears(58),
        9,
        seleccionArgentina,
        LocalDate.now().minusYears(5),
        1.80,
        150.0,
        Mediocampista,
        lider = false,
        "ARG"
    )
    val jugadorRoman = Jugador(
        "Roman",
        "shado",
        LocalDate.now().minusYears(35),
        5,
        seleccionArgentina,
        LocalDate.now().minusYears(3),
        1.90,
        150.0,
        Mediocampista,
        lider = false,
        "ARG"
    )
    val jugadorHenri = Jugador(
        "lucas",
        "Henri",
        LocalDate.now().minusYears(25),
        9,
        seleccionArgentina,
        LocalDate.now().minusYears(2),
        1.40,
        150.0,
        Delantero,
        lider = false,
        "ARG"
    )
    val jugadorRoberto = Jugador(
        "Roberto",
        "Carlo",
        LocalDate.now().minusYears(25),
        10,
        seleccionArgentina,
        LocalDate.now().minusYears(2),
        1.95,
        150.0,
        Delantero,
        lider = true,
        "ARG"
    )
    val jugadorPedro = Jugador(
        "Pedro",
        "Moro",
        LocalDate.now().minusYears(15),
        12,
        seleccionArgentina,
        LocalDate.now().minusYears(5),
        1.75,
        120.0,
        Delantero,
        lider = false,
        "ARG"
    )
    val jugadorJuan = Jugador(
        "Juan",
        "Claro",
        LocalDate.now().minusYears(15),
        99,
        seleccionArgentina,
        LocalDate.now().minusYears(1),
        1.35,
        80.0,
        Defensor,
        lider = false,
        "ARG"
    )
    val figuritaRiquelme = Figurita(10, NivelDeImpresion.ALTA, true, jugadorRiquelme)
    val figuritaAlberto = Figurita(2, NivelDeImpresion.BAJA, false, jugadorAlberto)
    val figuritaRoman = Figurita(3, NivelDeImpresion.BAJA, false, jugadorRoman)
    val figuritaHenri = Figurita(3, NivelDeImpresion.BAJA, false, jugadorHenri)
    val figuritaRoberto = Figurita(11, NivelDeImpresion.ALTA, true, jugadorRoberto)

    val figuritaJuan = Figurita(11, NivelDeImpresion.ALTA, true, jugadorJuan)
    val figuritaPedro = Figurita(15, NivelDeImpresion.MEDIA, false, jugadorPedro)
    usuarioMenorEdad.aniadirFiguritaRepetidas(figuritaRiquelme)
    usuarioMenorEdad.aniadirFiguritaRepetidas(figuritaAlberto)
    usuarioMenorEdad.aniadirFiguritaRepetidas(figuritaRoman)
    usuarioMenorEdad.aniadirFiguritaRepetidas(figuritaPedro)

    usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRiquelme)
    usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaAlberto)


    describe("Observer Test") {
        it("Usuario con mas de 5 figuritas faltantes") {
            usuarioMayorEdad.agregarAcciones(SiFaltan5FiguritasParaCompletar())
            usuarioMayorEdad.figuritasFaltantes.add(figuritaRiquelme)
            usuarioMayorEdad.figuritasFaltantes.add(figuritaAlberto)
            usuarioMayorEdad.figuritasFaltantes.add(figuritaRoman)
            usuarioMayorEdad.figuritasFaltantes.add(figuritaHenri)
            usuarioMayorEdad.figuritasFaltantes.add(figuritaRoberto)
            usuarioMayorEdad.notificarEvento(figuritaAlberto)
            // postObserver.siFaltan5FiguritasParaCompletar(usuarioMayorEdad)
            usuarioMayorEdad.kmCercania shouldBe 3 //si tenia el verificador tenia que ser 3 si no 9
        }
        it("Usuario con el album lleno") {
            //  postObserver.siLlenoElAlbumYTieneRegalables(usuarioMayorEdad)
            usuarioMayorEdad.notificarEvento(figuritaAlberto)
            usuarioMayorEdad.tipoDeUsuario.shouldBe(Desprendido)

        }
        it("Usuario con mas de 5 figuritas faltantes") {
            usuarioMayorEdad.agregarAcciones(SiFaltan5FiguritasParaCompletar())
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRoman)
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaHenri)
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRoberto)
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaJuan)
            usuarioMayorEdad.notificarEvento(figuritaRoman)
            usuarioMayorEdad.kmCercania shouldBe 3
        }
        it("Usuario con menos de 5 figuritas faltantes") {
            usuarioMayorEdad.agregarAcciones(SiFaltan5FiguritasParaCompletar())
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRoman)
            usuarioMayorEdad.solicitar(figuritaRoman, usuarioMenorEdad)
            usuarioMayorEdad.kmCercania shouldBe 9
        }
        it("Usuario con menos de 5 figuritas faltantes y que vuelve a realizar la accion, pero estara desabilitada") {
            usuarioMayorEdad.agregarAcciones(SiFaltan5FiguritasParaCompletar())
            usuarioMayorEdad.cantidadDeAcciones() shouldBe 1
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRoman)
            usuarioMayorEdad.solicitar(figuritaRoman, usuarioMenorEdad)
            usuarioMayorEdad.kmCercania shouldBe 9
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaPedro)
            usuarioMayorEdad.solicitar(figuritaPedro, usuarioMenorEdad)
            usuarioMayorEdad.cantidadDeAcciones() shouldBe 0
            usuarioMayorEdad.kmCercania shouldBe 9
        }
        it("Usuario con 3 solicitudes realizadas") {
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRoman)
            assertDoesNotThrow { usuarioMayorEdad.solicitar(figuritaRiquelme, usuarioMenorEdad) }
            assertDoesNotThrow { usuarioMayorEdad.solicitar(figuritaAlberto, usuarioMenorEdad) }
            assertDoesNotThrow { usuarioMayorEdad.solicitar(figuritaRoman, usuarioMenorEdad) }
            usuarioMayorEdad.agregarAcciones(siLosUltimos3intercambiosFueron())

            usuarioMayorEdad.notificarEvento(figuritaJuan)
            usuarioMayorEdad.tipoDeUsuario.shouldNotBe(Desprendido)//testeo que no es mas desprendido
        }
        it("usuario con 2 solicitudes de una seleccion y una de otra") {
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaPedro)
            assertDoesNotThrow { usuarioMayorEdad.solicitar(figuritaRiquelme, usuarioMenorEdad) }
            assertDoesNotThrow { usuarioMayorEdad.solicitar(figuritaAlberto, usuarioMenorEdad) }
            assertDoesNotThrow { usuarioMayorEdad.solicitar(figuritaPedro, usuarioMenorEdad) }
            usuarioMayorEdad.agregarAcciones(siLosUltimos3intercambiosFueron())
            usuarioMayorEdad.notificarEvento(figuritaPedro)
            usuarioMayorEdad.tipoDeUsuario.shouldNotBe(Desprendido)//testeo que es desprendido
        }
        it("Usuario con el album lleno") {

            usuarioMayorEdad.notificarEvento(figuritaRoberto)
            usuarioMayorEdad.tipoDeUsuario.shouldBe(Desprendido)

        }
        describe("siLaSolicitudFueExitosa") {
            listaDerepetidasReservadas.add(figuritaHenri)
            listaDerepetidasReservadas.add(figuritaJuan)
            it("no tiene repetidas")
            {
                usuarioMayorEdad.cantidadDeFiguritasRepetidas() shouldBe (0)
                usuarioMayorEdad.agregarAcciones(siLaSolicitudFueExitosa(listaDerepetidasReservadas))
                usuarioMayorEdad.solicitar(figuritaRiquelme, usuarioMenorEdad)
                usuarioMayorEdad.cantidadDeFiguritasRepetidas() shouldBe (1)
                usuarioMayorEdad.tieneRepetidaLa(figuritaJuan).shouldBeTrue()
            }
            it("tiene repetidas") {
                usuarioMayorEdad.aniadirFiguritaRepetidas(figuritaHenri)
                usuarioMayorEdad.solicitar(figuritaRiquelme, usuarioMenorEdad)
                usuarioMayorEdad.cantidadDeFiguritasRepetidas() shouldBe (1)
                usuarioMayorEdad.tieneRepetidaLa(figuritaJuan).shouldBeFalse()
            }

        }



        describe("SiLlenoElAlbumYTieneRegalables") {
            it("debe ser notificado si el usuario lleno el album y tiene figuritas regalables") {
                usuarioMayorEdad.agregarAcciones(SiLlenoElAlbumYTieneRegalables())
                usuarioMayorEdad.cantidadDeAcciones() shouldBe 1
                usuarioMayorEdad.solicitar(figuritaRiquelme, usuarioMenorEdad)
                usuarioMayorEdad.notificarEvento(figuritaRiquelme)
                usuarioMayorEdad.tipoDeUsuario.shouldBe(Desprendido)
            }

            it("no debe ser notificado si no lleno el album") {
                usuarioMayorEdad.agregarAcciones(SiLlenoElAlbumYTieneRegalables())
                usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRiquelme)
                usuarioMayorEdad.tieneFaltanteLa(figuritaRiquelme).shouldBeTrue()
                assertDoesNotThrow { usuarioMayorEdad.solicitar(figuritaRiquelme, usuarioMenorEdad) }

            }
        }

        describe("SiLlenoElAlbumConNotificacion") {
            it("si se lleno el album tiene que notificar con envio de mail") {
          /*usuarioMayorEdad.figuritasFaltantes.add(figuritaRiquelme)
            usuarioMenorEdad.figuritasRepetidas.add(figuritaRiquelme)
            usuarioMayorEdad.solicitar(figuritaRiquelme, usuarioMenorEdad)
            usuarioMayorEdad.seLlenoElAlbum() shouldBe true
            val mockedMailSender = mockk<MailSender>(relaxUnitFun = true)
            val lista = listaDeAccion().apply {
                    add(mockedMailSender)
                }
            MailSender = mockedMailSender
            verify(exactly = 1) { mockedMailSender.sendMail(mail = Mail(
              from="usuario1@usuario.com",
              to="usuario2@usuario.com, usuario3@usuario.com",
              subject="[algo2] Sale asado?",
              content = "Lo que dice el asunto")) }

            postObserver.siLlenoElAlbumConNotificacion(usuarioMayorEdad, mockedMailSender, figuritaRiquelme)
            */

            }
        }
    }
})




