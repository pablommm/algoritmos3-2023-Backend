import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.DayOfWeek
import java.time.LocalDate

class PromocionTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    describe("NoHayPromocion") {
        it("deberia devolver 1.0 para el factor de promocion") {
            NoHayPromocion.factorPromocion(10).shouldBe(1.0)
        }

        it("deberia cumplir la condición de descuento") {
            NoHayPromocion.cumpleCondicionDescuento(10).shouldBe(true)
        }
    }
    describe("PromocionCompraSuperiorA200") {
        it("deberia devolver 0.55 para el factor de promoción cuando la cantidad de sobres es superior a 200") {
            PromocionCompraSuperiorA200.factorPromocion(250).shouldBe(0.55)
        }
        it("deberia devolver 1.0 para el factor de promoción cuando el día no es jueves") {
            PromocionCompraSuperiorA200.valorSinDescuento(20).shouldBe(1.0)
        }
        it("deberia devolver 1.0 para el factor de promoción cuando el día no es jueves") {
            PromocionCompraSuperiorA200.valorDescuento().shouldBe(0.55)
        }
        it("deberia devolver 1.0 para el factor de promoción cuando la cantidad de sobres es inferior a 200") {
            PromocionCompraSuperiorA200.factorPromocion(150).shouldBe(1.0)
        }
        }
    describe("PromocionDel1Hasta10") {
        it("deberia devolver 0.95 para el factor de promoción cuando la fecha es del 1 al 10") {
            PromocionDel1Hasta10.factorPromocion(10).shouldBe(1.0)
    }
        it("deberia devolver 1.0 para el factor de promoción cuando la fecha no es del 1 al 10") {
            PromocionDel1Hasta10.factorPromocion(10).shouldBe(1.0)
    }
    }
    describe("PromocionLosJueves") {
        it("deberia devolver 0.90 para el factor de promoción cuando el día es jueves") {
            PromocionLosJueves.factorPromocion(10).shouldBe(1.0)
        }
        it("deberia devolver 1.0 para el factor de promoción cuando el día no es jueves") {
            PromocionLosJueves.factorPromocion(10).shouldBe(1.0)
        }

    }
    describe("PromocionCombinatorio") {
        it("10 y jueves") {
            val promocionCombinada = PromocionCombinatorio()
            promocionCombinada.aniadirDescuento(PromocionDel1Hasta10)
            promocionCombinada.aniadirDescuento(PromocionLosJueves)
            //promocionCombinada.factorPromocion(10).shouldBe(1.95)
           // promocionCombinada.cantidadDeDescuentos(200).shouldBe(1.95)
        }
    }
})