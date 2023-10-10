import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class posicionTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    //selecciones
    val racing = Seleccion("river", Confederacion.CONCACAF, 2, 9)
    val independiente = Seleccion("independiente", Confederacion.CONCACAF, 2, 9)
    val SeleccionBrasil = Seleccion("Brasil", Confederacion.CONCACAF, 2, 9)
    val SeleccionArgentina = Seleccion("Brasil", Confederacion.CONCACAF, 2, 9)
    val SeleccionFrancia = Seleccion("Francia", Confederacion.CONCACAF, 0, 7)
    val seleccionArgentina = Seleccion("argentina", Confederacion.CONCACAF, 0, 0)
    val bocaJuniors = Seleccion("BocaJuniors", Confederacion.CONCACAF, 3, 4)
    val seleccionUSA = Seleccion("argentina", Confederacion.CONCACAF, 5, 0)

    //lista de posiciones
    val posiciones :MutableList<Posicion> = mutableListOf(Delantero,Mediocampista)
    val posiciones2 :MutableList<Posicion> = mutableListOf(Delantero,Mediocampista,Arquero)

    //jugadores
    val jugadorEzequiel = Jugador("Ezequiel", "Palacios", LocalDate.now().minusYears(60), 1, racing, LocalDate.now().minusYears(22), 190.0, 150.0, Arquero, lider = false, "ARG", cotizacion = 2000)
    val jugadorEnzo= Jugador("Enzo", "Fernandez", LocalDate.now().minusYears(60), 1, racing, LocalDate.now().minusYears(22), 160.0, 150.0, Arquero, lider = false, "ARG", cotizacion = 2000)
    val jugadorMarcos = Jugador("Marcos", "Rojo", LocalDate.now().minusYears(60), 1, independiente, LocalDate.now().minusYears(15), 190.0, 150.0, Defensor, lider = true, "ARG", cotizacion = 2000)
    val jugadorCristiano = Jugador("Cristiano", "Ronaldo", LocalDate.now().minusYears(60), 1, independiente, LocalDate.now().minusYears(10), 160.0, 150.0, Defensor, lider = false, "ARG", cotizacion = 2000)
    val jugadorVinicius = Jugador("Junior", "Vinicius", LocalDate.now().minusYears(60), 1, SeleccionBrasil, LocalDate.now().minusYears(15), 190.0, 67.0, Mediocampista, lider = true, "ARG", cotizacion = 2000)
    val jugadorRicharlison = Jugador("Richarlison", "Andrade", LocalDate.now().minusYears(10), 1, SeleccionBrasil, LocalDate.now().minusYears(10), 160.0, 150.0, Mediocampista, lider = false, "ARG", cotizacion = 2000)
    val jugadorMaradona = Jugador("Diego", "Maradona", LocalDate.now().minusYears(60), 1, seleccionArgentina, LocalDate.now().minusYears(22), 1.80, 150.0, Polivalentes(posiciones), lider = false, "ARG", cotizacion = 2000)
    val jugadorTevez = Jugador("Carlos", "Tevez", LocalDate.now().minusYears(30), 10, bocaJuniors, LocalDate.now().minusYears(22), 180.0, 70.0, Polivalentes(posiciones), lider = true, "ARG", cotizacion = 30000000)
    val jugadorRonaldo = Jugador("Neymar", "Junior", LocalDate.now().minusYears(30), 10, bocaJuniors, LocalDate.now().minusYears(22), 180.0, 70.0, Polivalentes(posiciones2), lider = true, "ARG", cotizacion = 30000000)
    val jugadorHenri = Jugador("Diego", "Maradona", LocalDate.now().minusYears(60), 1, seleccionUSA, LocalDate.now().minusYears(9), 180.0, 67.0, Polivalentes(posiciones2), lider = false, "ARG", cotizacion = 40000000)
    val jugadorLionel = Jugador("Lionel", "Messi", LocalDate.now().minusYears(60), 10, SeleccionArgentina, LocalDate.now().minusYears(15), 190.0, 67.0, Delantero, lider = true, "ARG", cotizacion = 2000)
    val jugadorNeymar = Jugador("Neymar", "Junior", LocalDate.now().minusYears(15), 8, SeleccionFrancia, LocalDate.now().minusYears(10), 160.0, 150.0, Delantero, lider = false, "ARG", cotizacion = 2000)

    describe("Testeo de un jugador con posicion Arquero"){
        it("un jugador Arquero tiene una altura superior al requisito, su valoracion por posicion de 19000 "){
            jugadorEzequiel.calcularFactor() shouldBe (19000)
        }
        it("un jugador Arquero no tiene una altura superior a la requerida, su valoracion por posicion de 100"){
            jugadorEnzo.calcularFactor() shouldBe (100)
        }
    }

    describe("Testeo de un jugador con posicion Defensor"){
        it("un jugador defensor y lider su valoracion por posicion es 200 "){
            jugadorMarcos.calcularFactor() shouldBe (200)
        }
        it("un jugador defensor y no es lider su valoracion por posicion es 50"){
            jugadorCristiano.calcularFactor() shouldBe (50)
        }
    }

    describe("Testeo de un jugador con posicion Mediocampista"){
        it("un jugador mediocampista y ligero, su valoracion por posicion es 200 "){
            jugadorVinicius.calcularFactor() shouldBe (217)
        }
        it("un jugador mediocampista y no es lider su valoracion por posicion es 50"){
            jugadorRicharlison.calcularFactor() shouldBe (150)
        }
    }

    describe("Testeo de un jugador con posicion Delantero"){
        it("un jugador delantero y seleccion campeona del mundo, su valoracion por posicion es 300 "){
            jugadorLionel.calcularFactor() shouldBe (300)
        }
        it("un jugador delantero  y no es seleccion campeona del mundo, su valoracion por posicion es 200"){
            jugadorNeymar.calcularFactor() shouldBe (200)
        }
    }

    describe("Tests de posicion Polivalente") {
        it("un jugador es Mediocampista y Delantero pero no es leyenda ni promesa"){
            jugadorMaradona.calcularFactor() shouldBe (175)
        }
        it("un jugador es Mediocampista y Delantero, es leyenda pero no es promesa"){
            jugadorTevez.calcularFactor() shouldBe (405)
        }
    }

    describe("Tests Polivalente con tres posiciones") {
        it("un jugador es Mediocampista, Delantero y arquero , es leyenda pero no es promesa"){
            jugadorRonaldo.calcularFactor() shouldBe (6293)
        }
        it("un jugador es Mediocampista, Delantero y arquero , no es leyenda ni promesa"){
            jugadorHenri.calcularFactor() shouldBe (150)
        }
    }
    describe("test general de posicion"){
        it("un jugador decide cambiar de posicion"){
            jugadorRicharlison.cambiarPosicion(Delantero)
            jugadorRicharlison.posicion shouldBe (Delantero)
        }
    }
})


