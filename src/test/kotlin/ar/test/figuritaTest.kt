import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class figuritaTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val Portugal = Seleccion("Portugal", Confederacion.UEFA, 1, 1)
    val Ronaldo = Jugador(
        "cristiano",
        "Ronaldo",
        LocalDate.now().minusYears(30),
        7,
        Portugal,
        LocalDate.now().minusYears(14),
        1.87,
        83.0,
        Delantero,
        false,
        "Portugal"
    )

    val PSG = Seleccion("PSG", Confederacion.UEFA, 2, 2)
    val Neymar = Jugador(
        "Neymar",
        "Junior",
        LocalDate.now().minusYears(31),
        5,
        PSG,
        LocalDate.now().minusYears(14),
        1.70,
        80.0,
        Defensor,
        true,
        "Brasil"
    )
    val Boca = Seleccion("BOCA", Confederacion.UEFA, 3, 3)
    val Roman = Jugador(
        "Roman",
        "Riquelme",
        LocalDate.now().minusYears(44),
        5,
        Boca,
        LocalDate.now().minusYears(20),
        185.0,
        80.0,
        Arquero,
        true,
        "Buenos Aires"
    )

    it("Valoracion Base de una Figurita PAR con un nivel de Impresion BAJA") {
        val figuritaParImpBaja = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = false, Ronaldo, "pepe")
        figuritaParImpBaja.valorPorOnFire().shouldBe(100.0)
        figuritaParImpBaja.valorPorParidad().shouldBe(110.0.plusOrMinus(0.1))
        figuritaParImpBaja.valoracionBase().shouldBe(110.0.plusOrMinus(0.1))


    }
    it("Valoracion Base de una Figurita IMPAR y OnFire con un nivel de Impresion BAJA") {
        val figuritaInparImpBajaOnfire = Figurita(
            numero = 3, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe"
        )
        figuritaInparImpBajaOnfire.valoracionBase().shouldBe(120.0)
    }


    it("Valoracion Base de una Figurita IMPAR y OnFire con un nivel de Impresion Media ") {
        val figuritaInparImpMediaOnfire = Figurita(
            numero = 3, nivelDeImpresion = NivelDeImpresion.MEDIA, onFire = true, Ronaldo, imagen = "pepe"
        )
        figuritaInparImpMediaOnfire.valoracionBase().shouldBe(102.0)
    }
    it("Valoracion Base de una Figurita Par y OnFire con un nivel de Impresion Media ") {
        val figuritaParImpMediaOnfire = Figurita(
            numero = 2, nivelDeImpresion = NivelDeImpresion.MEDIA, onFire = true, Ronaldo, imagen = "pepe"
        )
        figuritaParImpMediaOnfire.valoracionBase().shouldBe(112.2)
    }
    it("Valoracion Base de una Figurita Par con un nivel de Impresion Media ") {
        val figuritaParImpMedia =
            Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.MEDIA, onFire = false, Ronaldo, imagen = "pepe")
        figuritaParImpMedia.valoracionBase().shouldBe(93.5.plusOrMinus(0.001))
    }
    it("Valoracion Base de una Figurita PAR ON FIRE con un nivel de Impresion BAJA ") {
        val figuritaParImpBajaOnfire = Figurita(
            numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe"
        )
        figuritaParImpBajaOnfire.valoracionBase().shouldBe(132.0)
    }
    it("Valoracion Base de una Figurita  IMPAR con un nivel de Impresion BAJA") {
        val figuritaIMparImpBaja =
            Figurita(numero = 3, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = false, Ronaldo, imagen = "pepe")
        figuritaIMparImpBaja.valoracionBase().shouldBe(100.0)
    }
    it("Valoracion Base de una Figurita IMPAR con un nivel de Impresion MEDIA") {
        val figuritaIMparImpMedia =
            Figurita(numero = 3, nivelDeImpresion = NivelDeImpresion.MEDIA, onFire = false, Ronaldo, imagen = "pepe")
        figuritaIMparImpMedia.valoracionBase().shouldBe(85.0)
    }
    it("Valoracion Base de una Figurita PAR con un nivel de Impresion ALTA") {
        val figuritaParImpALTA = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.ALTA, onFire = false, Ronaldo, imagen = "pepe")
        figuritaParImpALTA.valoracionBase().shouldBe(93.5.plusOrMinus(0.001))
    }
    it("Valoracion Base de una Figurita PAR ON FIRE con un nivel de Impresion ALTA ") {
        val figuritaParImpALTAOnfire = Figurita(
            numero = 2, nivelDeImpresion = NivelDeImpresion.ALTA, onFire = true, Ronaldo, imagen = "pepe"
        )
        figuritaParImpALTAOnfire.valoracionBase().shouldBe(112.2)
    }
    it("Valoracion Base de una Figurita IMPAR ON FIRE con un nivel de Impresion ALTA ") {
        val figuritaIMparImpALTAOnfire = Figurita(
            numero = 3, nivelDeImpresion = NivelDeImpresion.ALTA, onFire = true, Ronaldo, imagen = "pepe"
        )
        figuritaIMparImpALTAOnfire.valoracionBase().shouldBe(102.0)
    }
    it("Valoracion Base de una Figurita IMPAR con un nivel de Impresion ALTA") {
        val figuritaIMparImpALTA =
            Figurita(numero = 3, nivelDeImpresion = NivelDeImpresion.ALTA, onFire = false, Ronaldo, imagen = "pepe")
        figuritaIMparImpALTA.valoracionBase().shouldBe(85.0)
    }

    it("Se comprueba la paridad de una figurita ") {
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        val figuritaParidadimpar =
            Figurita(numero = 3, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.esPar().shouldBe(true)
        figuritaParidadimpar.esPar().shouldBe(false)

    }

    it("valoracion de posicion de un jugador delantero campeon del mundo ") {
        Ronaldo.valorPorPosicion().shouldBe(270.0)

    }

    it("valoracion de posicion de un jugador defensor lider") {
        Neymar.lider
        Neymar.aniosJugadosEnSeleccion()
        Neymar.valorPorPosicion().shouldBe(190.0)
    }

    it("anios jusgados en seleccion") {
        Neymar.aniosJugadosEnSeleccion().shouldBe(14)
    }

    it("valoracion de posicion de un jugador arquero") {
        Roman.valorPorPosicion().shouldBe(18500.0)
    }

    it("valoracion de posicion de un jugador arquero menor a 180") {
        val Roman2 = Jugador(
            "Roman",
            "Riquelme",
            LocalDate.now().minusYears(44),
            5,
            Boca,
            LocalDate.now().minusYears(20),
            175.0,
            80.0,
            Arquero,
            true,
            "Buenos Aires"
        )
        Roman2.valorPorPosicion().shouldBe(100.0)
    }


    it("valoracion de posicion de un jugador Defensor ") {
        val Roman2 = Jugador(
            "Roman",
            "Riquelme",
            LocalDate.now().minusYears(44),
            5,
            Boca,
            LocalDate.now().minusYears(20),
            179.0,
            80.0,
            Defensor,
            true,
            "Buenos Aires"
        )
        Roman2.aniosJugadosEnSeleccion().shouldBe(20)
        Roman2.valorPorPosicion().shouldBe(250.0)

    }
    it("valoracion de posicion de un jugador Defensor NO LIDER ") {
        val Roman2 = Jugador(
            "Roman",
            "Riquelme",
            LocalDate.now().minusYears(44),
            5,
            Boca,
            LocalDate.now().minusYears(20),
            179.0,
            80.0,
            Defensor,
            false,
            "Buenos Aires"
        )
        Roman2.aniosJugadosEnSeleccion().shouldBe(20)
        Roman2.valorPorPosicion().shouldBe(50.0)
    }

    it("Comprobamos que el metodo de busqueda funcione correctamente numero") {
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.coincideNumeroFigurita("2").shouldBe(true)
        figuritaParidadPar.esNivelDeImpresion(NivelDeImpresion.ALTA).shouldBe(false)
        figuritaParidadPar.esNivelDeImpresion(NivelDeImpresion.BAJA).shouldBe(true)

    }
    it (" validar figurita"){
        val figuritaParidadPar = Figurita(numero = -2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        assertThrows<GenericException> {figuritaParidadPar.validar()  }
    }
    it (" cambio onfire de figurita"){
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = false, Ronaldo, imagen = "pepe")
        figuritaParidadPar.cambiarFiguAOnFire()
        figuritaParidadPar.onFire.shouldBe(true)
    }
    it("metodo busqueda") {
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.busqueda("cristiano").shouldBe(true)
    }
    it ("Jugador con camiseta part"){
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.tieneJugadorConCamisetaPar().shouldBeFalse()
    }
    it(" Jugador Seleccion"){
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.figuritaSeleccion() shouldBe Portugal
    }
    it("Jugador no promesa pero si leyenda"){
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.esJugadorLeyenda().shouldBeFalse()
        figuritaParidadPar.esJugadorPromesa().shouldBeFalse()
    }
    it("Jugador Valoracion"){
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.BAJA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.valoracionFigurita() shouldBe 402.0
    }
    it("Jugador Valoracion SIN IMPRESION BAJA"){
        val Portugal = Seleccion("Portugal", Confederacion.UEFA, 1, 1)
        val Ronaldo = Jugador("cristiano","Ronaldo",LocalDate.now().minusYears(30), 7,Portugal,LocalDate.now().minusYears(14), 1.87,83.0,Delantero,false,"Portugal")
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.ALTA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.valoracionFigurita() shouldBe 382.2
    }
    it("test de validaciones"){
        val Portugal = Seleccion("Portugal", Confederacion.UEFA, 1, 1)
        val Ronaldo = Jugador("cristiano","Ronaldo",LocalDate.now().minusYears(30), 7,Portugal,LocalDate.now().minusYears(14), 1.87,83.0,Delantero,false,"Portugal")
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.ALTA, onFire = true, Ronaldo, imagen = "pepe")
        //figuritaParidadPar.validarNumero() shouldBe true
        figuritaParidadPar.esValidaNivelDeImpresion() shouldBe true
        figuritaParidadPar.esValidaOnFire() shouldBe true
        figuritaParidadPar.esValidaJugador() shouldBe true
        figuritaParidadPar.factorOnFire() shouldBe 1.2
        figuritaParidadPar.valoracionFigurita() shouldBe 382.2
        figuritaParidadPar.onFire = false
        figuritaParidadPar.factorOnFire() shouldBe 1.0
        figuritaParidadPar.valoracionFigurita() shouldBe 363.5
        figuritaParidadPar.factorParidad() shouldBe 1.1
        figuritaParidadPar.numero = 3
        figuritaParidadPar.factorParidad() shouldBe 1.0
        figuritaParidadPar.onFire = true
    }
    it("busqueda"){
        val Portugal = Seleccion("Portugal", Confederacion.UEFA, 1, 1)
        val Ronaldo = Jugador("cristiano","Ronaldo",LocalDate.now().minusYears(30), 7,Portugal,LocalDate.now().minusYears(14), 1.87,83.0,Delantero,false,"Portugal")
        val figuritaParidadPar = Figurita(numero = 2, nivelDeImpresion = NivelDeImpresion.ALTA, onFire = true, Ronaldo, imagen = "pepe")
        figuritaParidadPar.busqueda("cristiano").shouldBe(true)
        figuritaParidadPar.busqueda("Ronaldo").shouldBe(true)
        figuritaParidadPar.busqueda("Portugal").shouldBe(true)
        figuritaParidadPar.busqueda("2").shouldBe(true)

    }
    it("figurita Onfire"){
        val figuritaOnFire = Figurita(
            numero = 3, nivelDeImpresion = NivelDeImpresion.MEDIA, onFire = true, Ronaldo, imagen = "pepe"
        )
        assertDoesNotThrow { figuritaOnFire.ValidaOnFire() }
    }
    it("validar jugador de la figurita"){
        val figuritaDeJugador = Figurita(
            numero = 3, nivelDeImpresion = NivelDeImpresion.MEDIA, onFire = true, Ronaldo, imagen = "pepe"
        )
        assertDoesNotThrow {figuritaDeJugador.ValidaJugador()}
    }
    it("validar una figurita"){
        val figuritavalida = Figurita(
            numero = 3, nivelDeImpresion = NivelDeImpresion.MEDIA, onFire = true, Ronaldo, imagen = "pepe"
        )
        assertDoesNotThrow {figuritavalida.validar()}
    }


})

