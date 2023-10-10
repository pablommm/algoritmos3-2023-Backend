import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class PedidoTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Pedido") {
        val hoy = LocalDate.now()
        val figuritasFaltantes = mutableListOf<Figurita>()
        val figuritasRepetidas = mutableListOf<Figurita>()
        val fechaEstimada = hoy.plusDays(7)
        val pedido = Pedido(5, fechaEstimada)
        val direccionAvellaneda = Direccion(
            org.uqbar.geodds.Point(-34.661289687738716, -58.36713002783264),
            "Avellaneda",
            "Buenos Aires",
            "Av Mitre",
            750
        )
        val direccionAvellaneda2 =
            Direccion(org.uqbar.geodds.Point(-34.662374, -58.365065), "Avellaneda", "Buenos Aires", "Av Mitre", 750)
        val listadepedidos = mutableListOf<Pedido>()
        val kioscoJuanito = Kiosco(true, true, 170, "juanito", direccionAvellaneda, 5, pedido, listadepedidos,)
        val cobroDeUsuario = Usuario(
            name = "Jose",
            apellido = "Martinez",
            username = "JMartinez",
            fechaDeNacimiento = LocalDate.now().minusYears(30),
            "JMartinez@gmail.com",
            direccionAvellaneda2,
            figuritasFaltantes,
            figuritasRepetidas,
            Desprendido,
            kmCercania = 1000
        )

        /*        it("debería indicar si la fecha estimada de entrega es mayor a 10 días desde el pedido") {
            val resultado = pedido.esMayorA10Entrega()
            resultado shouldBe false
        }*/

        it("la fecha estimada de entrega se cumple ") {
            pedido.seCumpleDentroDe(10)shouldBe true

        }

        it("fecha estimada de entrega no se cumple ") {
           pedido.seCumpleDentroDe(5) shouldBe false
        }
        it("fecha estimada de entrega se cumple el dia limite") {
            pedido.seCumpleDentroDe(7) shouldBe true
        }
        it("fecha estimada de entrega no se cumple el dia limite") {
            pedido.seCumpleDentroDe(6) shouldBe false
        }
    }

})
