import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows

class SeleccionTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    val seleccionArgentina = Seleccion(
        "argentina",
        Confederacion.CONCACAF,
        13,
        7
    )
    val seleccionNigeria = Seleccion(
        "Nigeria",
        Confederacion.AFC,
        14,
        8,
    )
    val seleccionNombreInvalido = Seleccion(
    "",
    Confederacion.AFC,
    14,
    8,
)
    it("copas Par ") {
        seleccionArgentina.tieneCopasPares().shouldBe(true)
        seleccionNigeria.tieneCopasPares().shouldBe(true)
    }
    describe("confederacion valida") {
        seleccionArgentina.esValidoNombre().shouldBe(true)
        seleccionNigeria.esValidoNombre().shouldBe(true)
    }
    it("Argentina es Campeon Mundial") {
        seleccionArgentina.esCampeonMundial().shouldBe(true)
    }
    it("cantidad De copas de la seleccion argentina") {
        seleccionArgentina.cantidadDeCopasTotales().shouldBe(20)
    }
    it("es una seleccion con copas pares") {
        seleccionArgentina.tieneCopasPares().shouldBe(true)
    }
    it("es una seleccion con una confederacion valida") {
        seleccionArgentina.esValidaConfederacion().shouldBe(true)
    }

    it("es una seleccion con un nombre  invalido") {
        seleccionNombreInvalido.esValidoNombre().shouldBe(false)
    }
    it("coincide seleccion por parametro") {
        seleccionArgentina.coincideSeleccion("Argentina").shouldBe(true)
        seleccionNigeria.coincideSeleccion("nigeria").shouldBe(true)
    }

    it("no coincide seleccion por parametro") {
        seleccionArgentina.coincideSeleccion("Brasil").shouldBe(false)
        seleccionNigeria.coincideSeleccion("Alemania").shouldBe(false)
    }

    it("seleccion con nombre vacio no es valido") {
        seleccionNombreInvalido.esValidoNombre().shouldBe(false)
    }

    it("validar selecciones") {
        seleccionArgentina.validar()
    }
    it ("Seleccion no Valida Excepcion"){
        assertThrows<GenericException> {seleccionNombreInvalido.validar()}

        }
    it("validar cantidad de copas del mundo") {
        val seleccionCantidadCopasNegativa = Seleccion(
            "Equipo",
            Confederacion.CAF,
            -1,
            3
        )
        assertThrows<GenericException> { seleccionCantidadCopasNegativa.validarCantidadDeCopasMundo() }

    }

    it("validar cantidad de copas de confederación") {
        val seleccionCantidadCopasConfederacionNegativa = Seleccion(
            "Equipo",
            Confederacion.CAF,
            2,
            -1
        )
        assertThrows<GenericException> { seleccionCantidadCopasConfederacionNegativa.validarCantidadDeCopasConfederacion() }
    }



})
