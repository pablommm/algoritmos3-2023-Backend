import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class tipoDeUsuarioTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val seleccionesAConservar = mutableListOf<Seleccion>()

    val listaDeFiguritasFaltantes = mutableListOf<Figurita>()
    val listaDeFiguritasRepetidas = mutableListOf<Figurita>()
    val DireccionSanMartin =
        Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val usuario2 = Usuario(
        name = "jose",
        apellido = "Romero",
        username = "jromero",
        fechaDeNacimiento = LocalDate.now().minusYears(33),
        "Jromero@gmail.com",
        DireccionSanMartin,
        listaDeFiguritasFaltantes,
        listaDeFiguritasRepetidas,
        kmCercania = 1
    )
    val usuario3 = Usuario(
        name = "martin",
        apellido = "minuzzi",
        username = "mminuzzi",
        fechaDeNacimiento = LocalDate.now().minusYears(17),
        "mminuzzi@gmail.com",
        DireccionSanMartin,
        listaDeFiguritasFaltantes,
        listaDeFiguritasRepetidas,
        kmCercania = 0
    )
    val usuario4 = Usuario(
        name = "martin",
        apellido = "minuzzi",
        username = "mminuzzi",
        fechaDeNacimiento = LocalDate.now().minusYears(17),
        "mminuzzi@gmail.com",
        DireccionSanMartin,
        listaDeFiguritasFaltantes,
        listaDeFiguritasRepetidas,
        kmCercania = 0
    )

    val Argentina = Seleccion("argentina", Confederacion.CONCACAF, 13, 7)
    val Brasil = Seleccion("Brasil", Confederacion.CONCACAF, 1, 4)
    val jugadorRiquelme = Jugador(
        "Roman",
        "Riquelme",
        LocalDate.now().minusYears(20),
        9,
        Argentina,
        LocalDate.now().minusYears(1),
        1.80,
        150.0,
        Delantero,
        lider = true,
        "ARG",
        10000000
    )

    val jugadorApostador = Jugador(
        "Roman",
        "Riquelme",
        LocalDate.now().minusYears(23),
        9,
        Argentina,
        LocalDate.now().minusYears(3),
        1.80,
        150.0,
        Delantero,
        lider = false,
        "ARG",

    )
    val figuritaApostador = Figurita(3, NivelDeImpresion.BAJA, false, jugadorApostador)
    val jugadorJavier = Jugador(
        "Javier",
        "lopez",
        LocalDate.now().minusYears(21),
        3,
        Brasil,
        LocalDate.now().minusYears(3),
        1.60,
        100.0,
        Arquero,
        lider = false,
        "ARG",
        1000000
    )
    val jugadorRoman = Jugador(
        "Roman",
        "lopez",
        LocalDate.now().minusYears(26),
        3,
        Brasil,
        LocalDate.now().minusYears(15),
        1.90,
        120.0,
        Mediocampista,
        lider = false,
        "ARG",
        30000000
    )
    val figuritaRiquelme = Figurita(9, NivelDeImpresion.ALTA, false, jugadorRiquelme)
    val figuritaJavier = Figurita(3, NivelDeImpresion.BAJA, false, jugadorJavier)
    val jugador20MLiderycamiseta5y10 = Figurita(4, NivelDeImpresion.MEDIA, false, jugadorRoman)
    val usuario = Usuario(
        name = "Jose",
        apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        listaDeFiguritasFaltantes,
        listaDeFiguritasRepetidas,
        jugadorFavorito = jugadorApostador,
        kmCercania = 2
    )
    val conservadorUsuario = Usuario(
        name = "Jose",
        apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(16),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        listaDeFiguritasFaltantes,
        listaDeFiguritasRepetidas,
        jugadorFavorito = jugadorApostador,
        kmCercania = 2
    )

    val Portugal = Seleccion("Portugal", Confederacion.UEFA, 0, 1)
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
    val figuritaRonaldo = Figurita(
        numero = 2,
        nivelDeImpresion = NivelDeImpresion.MEDIA,
        onFire = true,
        Ronaldo
    )
    describe("test puede regalar figurita") {
        var listaDeSolicitudDeIntercambio : MutableList<Figurita>
        it("cambio de tipo de usuario Conservador y si puede regalar") {
            usuario.cambiarTipoUsuario(Conservador)
            figuritaRonaldo.nivelDeImpresion = NivelDeImpresion.ALTA
            usuario.figuritasFaltantes.add(figuritaRonaldo)
            usuario.seLlenoElAlbum() shouldBe false
            usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeFalse()
        }
        it("cambio de tipo de usuario Conservador y si puede regalar") {
            usuario.cambiarTipoUsuario(Conservador)
            usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeFalse()
            usuario.figuritasRepetidas.add(figuritaRonaldo)
            figuritaRonaldo.nivelDeImpresion = NivelDeImpresion.ALTA
            usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeTrue()
        }
        it("cambio de tipo de usuario Desprendido y si puede regalar") {
            usuario.cambiarTipoUsuario(Desprendido)
            usuario.figuritasRepetidas.add(figuritaRonaldo)
            usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeTrue()
        }
        it("cambio de tipo de usuario Fanatico y no puede regalar") {
            usuario.cambiarTipoUsuario(Fanatico)
            usuario.puedeRegalarFigurita(jugador20MLiderycamiseta5y10).shouldBeFalse()

        }
        it("cambio de tipo de usuario apostador y si puede regalar") {
            //Apostador: está dispuesto a entregar cualquier figurita que no esté OnFire
            // o no sea de una Promesa del Fútbol. Los jugadores promesas son los que
            // tienen no más de 22 años de edad, una cotización
            // inferior o igual a 20 millones de dólares y menos de 2 años en la Selección.
            usuario.cambiarTipoUsuario(Apostador)
            usuario.puedeRegalarFigurita(figuritaApostador).shouldBeFalse()
            /*figuritaApostador.onFire = false
            jugadorApostador.fechaDeNacimiento = LocalDate.now().minusYears(23)
            jugadorApostador.cotizacion = 2000
            jugadorApostador.anioDebut = LocalDate.now().minusYears(3)
            usuario.puedeRegalarFigurita(figuritaApostador).shouldBeTrue()*/
        }
        it("cambio de tipo de usuario interesado y si puede regalar") {
            listaDeFiguritasRepetidas.add(figuritaRonaldo)
            usuario.cambiarTipoUsuario(Interesado)
            usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeFalse()

        }
        it("cambio de tipo de usuario cambiante y si puede regalar") {
            conservadorUsuario.cambiarTipoUsuario(Cambiante)
            Cambiante.criterioActual(conservadorUsuario).shouldBe(Desprendido)
            //conservadorUsuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeTrue()
            conservadorUsuario.fechaDeNacimiento = LocalDate.now().minusYears(30)
            Cambiante.criterioActual(conservadorUsuario).shouldBe(Conservador)
            conservadorUsuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeFalse()

        }
        it("cambio de tipo de usuario cambiante y no puede regalar") {
            val usuario2 = Usuario(    name = "martin",
                apellido = "minuzzi",
                username = "mminuzzi",
                fechaDeNacimiento = LocalDate.now().minusYears(17),
                "mminuzzi@gmail.com",
                DireccionSanMartin,
                listaDeFiguritasFaltantes,
                listaDeFiguritasRepetidas,
                kmCercania = 0
            )
            usuario2.puedeRegalarFigurita(figuritaRonaldo).shouldBeFalse()
        }
        it("cambio de tipo de usuario a par y si puede o no regalar"){
            usuario.cambiarTipoUsuario(Par)
            usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeFalse()
            figuritaRonaldo.numero = 3
            //usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeTrue()

        }
        it("cambio de tipo de usuario a nacionalista y si puede regalar"){
            val seleccionesAConservar = mutableListOf<Seleccion>()
            val nacionalista=Nacionalista(seleccionesAConservar)
            usuario.cambiarTipoUsuario(nacionalista)
            nacionalista.agregarSeleccion(Portugal)
            usuario.puedeRegalarFigurita(figuritaRonaldo).shouldBeFalse()

        }
    }

})



