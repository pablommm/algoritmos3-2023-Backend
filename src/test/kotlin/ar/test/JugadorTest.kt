import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class JugadorTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val Portugal = Seleccion("Portugal", Confederacion.UEFA, 0, 1)
    val Ronaldo = Jugador("cristiano","Ronaldo",LocalDate.now().minusYears(30),7,Portugal,LocalDate.now().minusYears(14),187.0,83.0, Delantero,false,"Portugal",21000000)

    it("Camiseta entre 5 y 10") {
        Ronaldo.esCamisetaEntre().shouldBe(true)

    }
    it("la camiseta no esta entre 5 y 10"){
        val julio = Jugador("Julio","Cristiano",LocalDate.now().minusYears(20),3,Portugal,LocalDate.now().minusYears(14),187.0,83.0, Delantero,false,"Portugal",21000000)
        julio.esCamisetaEntre().shouldBe(false)
    }
    it("Jugador no tiene camiseta par") {
        Ronaldo.esJugadorConCamisetaPar().shouldBe(false)
    }

    it("Años de Jugador en la seleccion") {
        Ronaldo.aniosJugadosEnSeleccion().shouldBe(14)
    }
    it("Jugador Leyenda") {
        Ronaldo.esLeyenda().shouldBe(true)
    }
    it("Jugador cambia posicion") {
        Ronaldo.cambiarPosicion(Mediocampista)
        Ronaldo.posicion.shouldBe(Mediocampista)
    }

    it("Años del Jugador") {
        Ronaldo.aniosDelJugador().shouldBe(30)
    }
    it("Cotizacion del jugador superior") {
        Ronaldo.superaCotizacionInferior().shouldBe(true)
    }
    it("no es un jugador promesa") {
        Ronaldo.tieneEdadPromesa().shouldBe(false)
        Ronaldo.tieneAntiguedadPromesa().shouldBe(false)
        Ronaldo.esPromesaDeFutbol().shouldBe(false)
    }
    it("Jugador con copas pares") {
        Ronaldo.tieneSeleccionConCopasPar().shouldBe(false)
    }
    it("Jugador pertenece a una seleccion campeon mundial ") {
        Ronaldo.perteneceAUnaSeleccionCampeona().shouldBe(false)
    }
    it("altura jugador maoyr al 180") {
        Ronaldo.tieneOEsMasAltoDe(180.0).shouldBe(true)
    }
    it("Validacion de jugador") {
        Ronaldo.validar()
    }
    it("Jugador Nombre no valido") {
        val JugadorNoValido = Jugador("","",LocalDate.now().minusYears(30),-7,Portugal,LocalDate.now().minusYears(14),-187.0,-83.0, Delantero,false,"Portugal",21000000)
        assertThrows<ExceptionNombre> { JugadorNoValido.validar() }
    }
    it("Jugador no valido") {
        val JugadorNoValido = Jugador("","",LocalDate.now().minusYears(30),-7,Portugal,LocalDate.now().minusYears(14),-187.0,-83.0, Delantero,false,"Portugal",21000000)
        assertThrows<genericException> { JugadorNoValido.validarApellido() }
        assertThrows<genericException> { JugadorNoValido.validarNombre() }
        assertThrows<genericException> { JugadorNoValido.validarnroDeCamiseta() }
        assertThrows<genericException> { JugadorNoValido.validarAltura() }
        assertThrows<genericException> { JugadorNoValido.validarPeso() }
    }
    it ("metodo de busqueda"){
        Ronaldo.coincideSeleccion("Portugal").shouldBe(true)
        Ronaldo.coincideParcialApellidoJugador("Ronaldo").shouldBe(true)
        Ronaldo.coincideParcialNombreJugador("cristiano").shouldBe(true)
    }
    it("criterio de busqeuda"){
        Ronaldo.criterioDeBusqueda("Portugal").shouldBe(true)
        Ronaldo.criterioDeBusqueda("Ronaldo").shouldBe(true)
    }
    it ("metodo busqueda"){
        Ronaldo.busqueda("Portugal").shouldBe(true)
        Ronaldo.busqueda("Ronaldo").shouldBe(true)
    }










})