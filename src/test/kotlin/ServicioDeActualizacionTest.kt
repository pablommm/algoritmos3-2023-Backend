import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.reflection.beLateInit
import io.kotest.matchers.shouldBe
import java.time.LocalDate


class ServicioDeActualizacionTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val stubservice = StubSelecciones()
    val actualizador = CreateOrUpdateSeleccion(stubservice)
    val stubserviceCreate =StubSeleccionesCreate()
    val create = CreateOrUpdateSeleccion(stubserviceCreate)

    describe("prueba de service"){
        it("prueba de create or update"){
            val listaDeJson = actualizador.jsonALista()
            val repositorio = Repositorio<Seleccion>()
            listaDeJson.size shouldBe 6

        }
        it ("Prueba de create"){
            val actualizador = CreateOrUpdateSeleccion(StubSeleccionesCreate())
            val listaAJson = create.jsonALista()
            val repositori2 = Repositorio<Seleccion>()
            actualizador.actualizar(repositori2)
            listaAJson.size shouldBe 1
            repositori2.getById(1).nombre shouldBe "Alemania"
        }

    }
})