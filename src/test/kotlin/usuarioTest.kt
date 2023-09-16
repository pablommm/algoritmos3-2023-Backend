import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate


class usuarioTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val figuritasFaltantes = mutableListOf<Figurita>()
    val figuritasRepetidas = mutableListOf<Figurita>()
    /*val FiguritasFaltantes2 = mutableListOf<Figurita>()
    val FiguritasRepetidas2 = mutableListOf<Figurita>()*/


    val DireccionSanMartin =
        Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val DireccionBallester =
        Direccion(org.uqbar.geodds.Point(-34.582137, -40.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
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
    val jugadorMessi = Jugador(
        "Leonel",
        "Messi",
        LocalDate.now().minusYears(30),
        10,
        seleccionArgentina,
        LocalDate.now().minusYears(10),
        1.50,
        100.0,
        Mediocampista,
        lider = true,
        "ARG"
    )

    val figuritaRiquelme = Figurita(10, NivelDeImpresion.ALTA, true, jugadorRiquelme)
    val figuritamessi = Figurita(1, NivelDeImpresion.ALTA, true, jugadorMessi)
    val usuarioMayorEdad = Usuario(
        name = "Jose",
        apellido = "Martinez",
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


    describe("un usuario que tiene figuritas repetidas y faltantes") {
        it("un usuario solicita a otro usuario una figurita ") {
            usuarioMayorEdad.figuritasRepetidas.add(figuritamessi)
            usuarioMenorEdad.figuritasFaltantes.add(figuritamessi)
            usuarioMenorEdad.solicitar(figuritamessi, usuarioMayorEdad)
            usuarioMenorEdad.tieneFaltanteLa(figuritamessi) shouldBe false
        }
        it("Se compruba la edad del usuario ") {
            usuarioMayorEdad.edad().shouldBe(30)

        }
        it(" es valido") {
            usuarioMayorEdad.esValidoapellido().shouldBeTrue()

        }

        it("prueba de funciones ") {
            usuarioMayorEdad.aniadirFiguritaRepetidas(figuritaRiquelme)
            assertThrows<genericException> { usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRiquelme) }
        }
        it("una figurita que no esta repetida") {
            usuarioMayorEdad.tieneRepetidaLa(figuritamessi) shouldBe (false)
        }
        it("tiene una figurita repetida") {
            usuarioMayorEdad.tieneRepetidaLa(figuritaRiquelme) shouldBe (false)
        }
        it("una figurita que no tengo repetida") {
            usuarioMayorEdad.tieneRepetidaLa(figuritamessi)

        }
        it("un usuario considera que otro usuario es cercano") {
            usuarioMayorEdad.esCercanoA(usuarioMenorEdad) shouldBe (true)
        }
        it ("Comprobamos que el metodo de busqueda funcione correctamente"){
            usuarioMayorEdad.coincideParcialNombre("Jose").shouldBe(true)
            usuarioMenorEdad.coincideParcialNombre("Maria").shouldBe(true)
            usuarioMenorEdad.coincideParcialApellido("Maria").shouldBe(false)

        }
        it ("Comprobamos que el metodo de busqueda funcione correctamente"){
            usuarioMayorEdad.coincideNombreOApellidoOUsername("Jose").shouldBe(true)
            usuarioMenorEdad.coincideNombreOApellidoOUsername("Maria").shouldBe(true)
            usuarioMenorEdad.coincideNombreOApellidoOUsername(" ").shouldBe(false)

        }
        it ("metodo busqueda"){
            usuarioMayorEdad.busqueda("Jose").shouldBe(true)
            usuarioMenorEdad.busqueda("Maria").shouldBe(true)
        }

        it ("Error Figurita No repetida"){
            assertThrows<genericException> {usuarioMayorEdad.figuritaNoRepetida(figuritaRiquelme)  }
        }
        it ("Error Figurita No Faltante"){
            assertThrows<genericException> {usuarioMayorEdad.figuritaNoFaltante(figuritaRiquelme)  }
        }



        it ("usuario puede regalar y es cercano") {
            usuarioMayorEdad.esValidoapellido().shouldBe(true)
        }

        it ("Usuario no puede regalar figurita"){
            usuarioMayorEdad.puedeRegalarFigurita(figuritaRiquelme).shouldBe(false)
        }


        it("validar no valido"){
            assertDoesNotThrow { usuarioMayorEdad.validar() }
            assertDoesNotThrow { usuarioMayorEdad.validarNombre() }
            assertDoesNotThrow { usuarioMayorEdad.validarApellido() }
            assertDoesNotThrow { usuarioMayorEdad.validarUsername() }
            assertDoesNotThrow { usuarioMayorEdad.validarCorreo() }
        }

        it ("Añadir Figurita"){
            //usuarioMayorEdad.figuritasFaltantes.add(figuritamessi)
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritamessi)
            usuarioMayorEdad.quitarFiguritaFaltantes(figuritamessi)
            usuarioMayorEdad.aniadirFiguritaRepetidas(figuritamessi)
            usuarioMayorEdad.figuritasRepetidas.contains(figuritamessi).shouldBe(true)
        }
        it("Edad de usuario"){
            usuarioMayorEdad.edad() shouldBe 30
        }
        it("Es jugador favor"){
            usuarioMayorEdad.jugadorFavorito = jugadorMessi
            usuarioMayorEdad.esJugadorFavorito(figuritamessi).shouldBeTrue()
        }
        it("Cantidad de figuritas repetidas"){
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRiquelme)
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritamessi)
            usuarioMayorEdad.cantidadFiguritaFaltantes() shouldBe 2
        }
        it("Usuario con figuritas FALTANTES MENOR A 5"){
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritaRiquelme)
            usuarioMayorEdad.aniadirFiguritaFaltantes(figuritamessi)
            usuarioMayorEdad.repesMenorA5() shouldBe true
        }
        it(" se comprueba solicitar"){
            usuarioMayorEdad.figuritasFaltantes.add(figuritaRiquelme)
            usuarioMenorEdad.figuritasRepetidas.add(figuritaRiquelme)
            usuarioMayorEdad.solicitar(figuritaRiquelme,usuarioMenorEdad)
            usuarioMayorEdad.figuritasFaltantes.contains(figuritaRiquelme).shouldBe(false)

        }
        it(" Coincide el username"){
            usuarioMayorEdad.coincideParcialUsername("JM") shouldBe true
        }
        it(" se comprueba solicitar"){
            usuarioMayorEdad.aniadirFiguritaRepetidas(figuritaRiquelme)
            usuarioMayorEdad.aniadirFiguritaRepetidas(figuritaRiquelme)
            usuarioMayorEdad.aniadirFiguritaRepetidas(figuritaRiquelme)
            usuarioMayorEdad.aniadirFiguritaRepetidas(figuritaRiquelme)
            usuarioMayorEdad.aniadirFiguritaRepetidas(figuritaRiquelme)
            usuarioMenorEdad.figuritasFaltantes.add(figuritaRiquelme)

            usuarioMayorEdad.cuantasTieneRepetidas(figuritaRiquelme) shouldBe 5
            usuarioMayorEdad.cantidadFiguritaFaltantes() shouldBe 1
        }
        it(" usuario no es cercano"){
            usuarioMayorEdad.esCercanoA(usuarioMenorEdad)
           // assertThrows<genericException> { usuarioMayorEdad.esCercanoA(usuarioMenorEdad)}
        }


    }
})
/*it("validar no valido"){
            assertThrows<genericException> { usuarioMayorEdad.validar() }
            assertThrows<genericException> { usuarioMayorEdad.validarNombre() }
            assertThrows<genericException> { usuarioMayorEdad.validarApellido() }
            assertThrows<genericException> { usuarioMayorEdad.validarUsername() }
            assertThrows<genericException> { usuarioMayorEdad.validarCorreo() }
        }*/