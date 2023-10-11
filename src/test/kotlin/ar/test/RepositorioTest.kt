import edu.Repositorio
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

    class RepositorioTest : DescribeSpec(body = {
        isolationMode = IsolationMode.InstancePerTest
        describe("Repositorio de usuarios") {
            val listaDeFiguritasFaltantes = mutableListOf<Figurita>()
            val listaDeFiguritasRepetidas = mutableListOf<Figurita>()
            val DireccionSanMartin = Direccion(
                org.uqbar.geodds.Point(-34.582137, -58.520687),
                "San Martin",
                "Buenos Aires",
                "25 de Mayo",
                1653
            )

            val seleccionArgentina = Seleccion("argentina", Confederacion.CONCACAF, 13, 7)
            val seleccionMexico = Seleccion("mexico", Confederacion.CONCACAF, 13, 7)

            val usuarioPruebaRepo = Usuario(
                name = "Jose",
                apellido = "Martinez",
                username = "JMartinez",
                fechaDeNacimiento = LocalDate.now().minusYears(30),
                email = "JMartinez@gmail.com",
                DireccionSanMartin,
                listaDeFiguritasFaltantes,
                listaDeFiguritasRepetidas
            )
            val usuarioPruebaRepo2 = Usuario(
                name = "Luis",
                apellido = "Manrrique",
                username = "LManrrique",
                fechaDeNacimiento = LocalDate.now().minusYears(30),
                email = "LLManrrique@gmail.com",
                DireccionSanMartin,
                listaDeFiguritasFaltantes,
                listaDeFiguritasRepetidas
            )

            val jugadorPruebaRepo = Jugador(
                nombre ="Roman",
                apellido ="Riquelme",
                LocalDate.now().minusYears(75),
                nroDeCamiseta = 10,
                seleccionArgentina,
                anioDebut = LocalDate.now().minusYears(10),
                altura = 1.80,
                peso = 150.0,
                posicion = Mediocampista,
                lider = true,
                pais = "ARG"
            )

            val jugadorPruebaRepo2 = Jugador(
                nombre ="Emanuel",
                apellido ="Riquelme",
                LocalDate.now().minusYears(75),
                nroDeCamiseta = 10,
                seleccionMexico,
                anioDebut = LocalDate.now().minusYears(10),
                altura = 1.80,
                peso = 150.0,
                posicion = Mediocampista,
                lider = true,
                pais = "ARG"
            )

            val usuariosRepo = Repositorio<Usuario>()

            it("probando agregado de elementos al repositorio usuario") {
                usuariosRepo.create(usuarioPruebaRepo)
                usuarioPruebaRepo.id.shouldBe(1)
                usuariosRepo.create(usuarioPruebaRepo2)
                usuarioPruebaRepo2.id.shouldBe(2)
                usuariosRepo.elementos.contains(usuarioPruebaRepo).shouldBe(true)
                usuariosRepo.elementos.contains(usuarioPruebaRepo2).shouldBe(true)
                usuariosRepo.elementos.count().shouldBe(2)
            }

            it("probando eliminar un elemento al repositorio usuario") {
                usuariosRepo.create(usuarioPruebaRepo)
                usuariosRepo.create(usuarioPruebaRepo2)
                usuariosRepo.elementos.count().shouldBe(2)
                usuariosRepo.delete(usuarioPruebaRepo)
                usuariosRepo.elementos.contains(usuarioPruebaRepo) shouldBe false
                usuariosRepo.elementos.contains(usuarioPruebaRepo2) shouldBe true
                usuariosRepo.elementos.count().shouldBe(1)

            }
            it("probando update de un elemento al repositorio usuario") {
                usuariosRepo.create(usuarioPruebaRepo)
                usuarioPruebaRepo.id.shouldBe(1)
                //usuariosRepo.update(usuarioPruebaRepo2)
                //usuarioPruebaRepo2.id.shouldBe(0)


            }
            it("probando find de un elemento al repositorio usuario") {
                usuariosRepo.create(usuarioPruebaRepo)
                usuariosRepo.create(usuarioPruebaRepo2)
                usuariosRepo.search("JMartinez") shouldBe listOf(usuarioPruebaRepo)
                usuariosRepo.search("Luis") shouldBe listOf(usuarioPruebaRepo2)
            }


            val jugadorRepo = Repositorio<Jugador>()

            it("probando agregado de elementos al repositorio jugador") {
                jugadorRepo.create(jugadorPruebaRepo)
                jugadorRepo.create(jugadorPruebaRepo2)
                jugadorPruebaRepo2.id.shouldBe(2)
                jugadorRepo.elementos.contains(jugadorPruebaRepo).shouldBe(true)
                jugadorRepo.elementos.contains(jugadorPruebaRepo2).shouldBe(true)
                jugadorRepo.elementos.count().shouldBe(2)
            }

            it("probando eliminar un elemento al repositorio jugador") {
                jugadorRepo.create(jugadorPruebaRepo)
                jugadorRepo.create(jugadorPruebaRepo2)
                jugadorRepo.elementos.count().shouldBe(2)
                jugadorRepo.delete(jugadorPruebaRepo)
                jugadorRepo.elementos.contains(jugadorPruebaRepo) shouldBe false
                jugadorRepo.elementos.contains(jugadorPruebaRepo2) shouldBe true
                jugadorRepo.elementos.count().shouldBe(1)

            }

            it("probando update de un elemento al repositorio jugador") {
                jugadorRepo.create(jugadorPruebaRepo)
                jugadorPruebaRepo.id.shouldBe(1)


            }
            //it("probando find de un elemento al repositorio jugador") {
              //  jugadorRepo.create(jugadorPruebaRepo)
                //jugadorRepo.create(jugadorPruebaRepo2)
               // jugadorRepo.search("Riquelme") shouldBe listOf(jugadorPruebaRepo)
                //jugadorRepo.search("Roman") shouldBe listOf(jugadorPruebaRepo2)
            //}
        }


        describe("Repositorio de Seleccion") {
            val seleccionPruebaRepo = Seleccion(
                "Argentina",
                Confederacion.CONMEBOL,
                3,
                4,
            )
            val seleccionPruebaRepo2 = Seleccion(
                "Brasil",
                Confederacion.CONMEBOL,
                4,
                3,
            )

            val seleccionRepo = Repositorio<Seleccion>()

            it("probando agregado de elementos al repositorio seleccion") {
                seleccionRepo.create(seleccionPruebaRepo)
                seleccionPruebaRepo.id.shouldBe(1)
                seleccionRepo.create(seleccionPruebaRepo2)
                seleccionPruebaRepo2.id.shouldBe(2)
                seleccionRepo.elementos.contains(seleccionPruebaRepo).shouldBe(true)
                seleccionRepo.elementos.contains(seleccionPruebaRepo2).shouldBe(true)
                seleccionRepo.elementos.count().shouldBe(2)

            }

            it("probando eliminar un elemento al repositorio de seleccion") {
                seleccionRepo.create(seleccionPruebaRepo)
                seleccionRepo.create(seleccionPruebaRepo2)
                seleccionRepo.elementos.count().shouldBe(2)
                seleccionRepo.delete(seleccionPruebaRepo)
                seleccionRepo.elementos.count().shouldBe(1)
                seleccionRepo.elementos.contains(seleccionPruebaRepo) shouldBe false
                seleccionRepo.elementos.contains(seleccionPruebaRepo2) shouldBe true

            }
            it("probando update de un elemento al repositorio seleccion") {
                seleccionPruebaRepo2.id=1
                seleccionRepo.create(seleccionPruebaRepo)
                seleccionPruebaRepo.id.shouldBe(1)
                seleccionRepo.elementos.contains(seleccionPruebaRepo) shouldBe true
                seleccionRepo.elementos.contains(seleccionPruebaRepo2) shouldBe false
                seleccionRepo.update(seleccionPruebaRepo2)
                seleccionPruebaRepo2.id.shouldBe(1)
                seleccionRepo.elementos.contains(seleccionPruebaRepo) shouldBe false
                seleccionRepo.elementos.contains(seleccionPruebaRepo2) shouldBe true

            }

            it("probando find de un elemento al repositorio seleccion") {
                seleccionRepo.create(seleccionPruebaRepo)
                seleccionRepo.create(seleccionPruebaRepo2)
                seleccionRepo.search("Argentina") shouldBe listOf(seleccionPruebaRepo)
                seleccionRepo.search("Brasil") shouldBe listOf(seleccionPruebaRepo2)
            }
            it("probando getById de un elemento al repositorio seleccion") {
                seleccionRepo.create(seleccionPruebaRepo)
                seleccionRepo.create(seleccionPruebaRepo2)
                seleccionRepo.getById(1) shouldBe seleccionPruebaRepo
                seleccionRepo.getById(2) shouldBe seleccionPruebaRepo2
            }
        }

    })









