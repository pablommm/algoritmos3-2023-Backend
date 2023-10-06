import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.junit.jupiter.api.assertDoesNotThrow
import java.time.DayOfWeek
import java.time.LocalDate

class PuntosDeVentasTest : DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest
    val listaDePedido = mutableListOf<Pedido>()
    val listaDeFiguritasFaltantes = mutableListOf<Figurita>()
    val listaDeFiguritasRepetidas = mutableListOf<Figurita>()
    val figuritasFaltantes = mutableListOf<Figurita>()
    val figuritasRepetidas = mutableListOf<Figurita>()
    var listaDeDescuentos: MutableList<Promocion> = mutableListOf<Promocion>()
    val DireccionSanMartin = Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val usuarioMayorEdad = Usuario(
        name = "Jose",
        apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        listaDeFiguritasFaltantes,
        listaDeFiguritasRepetidas
    )
    fun stubDiaJueves(respuesta: Boolean): PromocionLosJueves {
        val descuento = spyk<PromocionLosJueves>()
        every { descuento.cumpleCondicionDescuento(any()) } returns respuesta
        return descuento
    }
    fun stubPrimerosDíasDelMes(respuesta: Boolean): PromocionDel1Hasta10 {
        val descuento = spyk<PromocionDel1Hasta10>()
        every { descuento.cumpleCondicionDescuento(any()) } returns respuesta
        return descuento
    }
    val pedidoDe50sobres = Pedido(50, LocalDate.now())
    val pedidoDe100sobres = Pedido(100, LocalDate.now())
    val pedido = Pedido(100, LocalDate.now())
    val pedido300 = Pedido(300, LocalDate.now())
    val pedidoJulio = Pedido(100, LocalDate.of(2023, 7, 16))
    val promocionDel1Hasta10 = PromocionDel1Hasta10
    val direccionKioscoCercano = Direccion(org.uqbar.geodds.Point(-34.581669, -58.522666), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val direccionKioscoLejano = Direccion(org.uqbar.geodds.Point(-853.582137, -760.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val kioscoCercano = Kiosco(false, false, 170, "lacardeuse", direccionKioscoCercano, 50, pedidoDe100sobres, listaDePedido)
    val kioscoLejano = Kiosco(true, true, 170, "lejano", direccionKioscoLejano, 50, pedidoDe50sobres, listaDePedido)
    val libreriaRecibeDentroDe10 = Libreria(170, "Proxima Entrega", direccionKioscoCercano, 200, pedido, listaDePedido)
    val libreriaNoRecibeDentroDe10 = Libreria(170, "no pedidos", direccionKioscoCercano, 200, pedidoJulio, listaDePedido)
    val SupermercadoSinPromocion = Supermercado(170, "Promocion", DireccionSanMartin, 200, pedido, promocionDel1Hasta10, listaDePedido)
    val supermercadoConPromocion = Supermercado(170, "Promocion", DireccionSanMartin, 200, pedido, NoHayPromocion, listaDePedido)
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
    val kioscoJuanito = Kiosco(true, true, 170, "juanito", direccionAvellaneda, 5, pedido, listadepedidos)
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
    describe("Kioscos") {


        it("debería calcular el factor del dueño correctamente") {
            kioscoCercano.factorDuenio() shouldBe 1.0
            kioscoLejano.factorDuenio() shouldBe 0.10
        }

        it("debería calcular el factor del empleado correctamente") {
            kioscoCercano.factorEmpleado() shouldBe 1.0
            kioscoLejano.factorEmpleado() shouldBe 0.25
        }
        it("punto de venta kiosco juanito") {
            kioscoJuanito.esCercano(cobroDeUsuario) shouldBe true
            kioscoJuanito.importeACobrar(cobroDeUsuario, 1) shouldBe 1579.5
            direccionAvellaneda.distancia(direccionAvellaneda) shouldBe 0.0 //no se si esta bien
            kioscoJuanito.cantidadDeKM(cobroDeUsuario) shouldBe 1.0
        }

    }
    describe("Librerias") {


        it(" entrega es mayor a 10 días") {
            val resultadoRecibeDentroDe10 = libreriaRecibeDentroDe10.entregaDentroDeLos10()
            resultadoRecibeDentroDe10 shouldBe true

            val resultadoNoRecibeDentroDe10 = libreriaNoRecibeDentroDe10.entregaDentroDeLos10()
            resultadoNoRecibeDentroDe10 shouldBe false
        }
        it("entrega superior a 10 días") {
            val resultadoRecibeDentroDe10 = libreriaRecibeDentroDe10.factorEntregaSuperiorA10()
            resultadoRecibeDentroDe10 shouldBe 1.1

            val resultadoNoRecibeDentroDe10 = libreriaNoRecibeDentroDe10.factorEntregaSuperiorA10()
            resultadoNoRecibeDentroDe10 shouldBe 1.05
        }
        it("libreria piola") {
            libreriaRecibeDentroDe10.importeACobrar(usuarioMayorEdad, 1) shouldBe 1287.0
            libreriaNoRecibeDentroDe10.importeACobrar(usuarioMayorEdad, 1) shouldBe 1228.5
        }
    }

    describe("supermercado") {



        it("no hay promocion") {
            supermercadoConPromocion.cambioDePromocion(NoHayPromocion)
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 1) shouldBe 1170.0
        }
        it("testeo de promocion superior a 200") {
            supermercadoConPromocion.promocion.cumpleCondicionDescuento(1)
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 1) shouldBe 1170.0
            supermercadoConPromocion.cambioDePromocion(PromocionCompraSuperiorA200)
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 201) shouldBe 19343.5
        }
        it("importa a cobrar con promocion combinatoria de 1 al 10 y compra superior a 200") {
            supermercadoConPromocion.cambioDePromocion(PromocionCombinatorio(listaDeDescuentos))
            PromocionCombinatorio().aniadirDescuento(promocionDel1Hasta10)
            PromocionCombinatorio().aniadirDescuento(PromocionCompraSuperiorA200)
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 201) shouldBe 17585.0
        }
        it("funcion busqueda") {
            supermercadoConPromocion.busqueda("Promocion") shouldBe true
        }
        it("testeando sobres disponibles") {
            supermercadoConPromocion.stockDeSobres = 500
            supermercadoConPromocion.hayDisponibilidad() shouldBe true
            SupermercadoSinPromocion.stockDeSobres = 0
            SupermercadoSinPromocion.hayDisponibilidad() shouldBe false
        }
        it("testeando reciboPedidoDeFabrica") {
            supermercadoConPromocion.stockDeSobres = 500
            supermercadoConPromocion.ventaPor(100)
            supermercadoConPromocion.stockDeSobres = 0
            shouldThrow<Exception> { supermercadoConPromocion.ventaPor(100) }
        }
        it("testeo de validaciones") {
            supermercadoConPromocion.stockDeSobres = 500
            supermercadoConPromocion.esValidoNombre() shouldBe true
            supermercadoConPromocion.esValidoStockDeSobre() shouldBe true
            supermercadoConPromocion.esValidoPedidoPendientesDeEntrega() shouldBe true
            supermercadoConPromocion.stockDeSobres = -200
            supermercadoConPromocion.esValidoStockDeSobre() shouldBe false
            supermercadoConPromocion.nombre = ""
            supermercadoConPromocion.esValidoNombre() shouldBe false
        }
        it("validaciones validas y no validas") {
            assertDoesNotThrow { supermercadoConPromocion.validarNombre() }
            assertDoesNotThrow { supermercadoConPromocion.validarStockDeSobres() }
            assertDoesNotThrow { supermercadoConPromocion.validarPedidoPendientesDeEntrega() }
            assertDoesNotThrow { supermercadoConPromocion.validacionDeVenta(0) }
            assertDoesNotThrow { supermercadoConPromocion.validar() }
        }
        it("pedido prueba") {
            supermercadoConPromocion.reciboPedidoDeFabrica(pedido300)
        }
        it("controlamos lista de pedidos") {
            supermercadoConPromocion.listaDePedidosPendientes.add(pedido)
            supermercadoConPromocion.listaDePedidosPendientes.add(pedido300)
            supermercadoConPromocion.listaDePedidosPendientes.size shouldBe 2
        }
        it("test costo por pedido") {
            supermercadoConPromocion.costoPorPedido(100, usuarioMayorEdad) shouldBe 17000.0
        }
        it("test de costo minimo") {
            supermercadoConPromocion.costoMinimo(100, usuarioMayorEdad) shouldBe 18000.0
        }
        it("test factor punto de venta") {
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 100) shouldBe 18000.0
            supermercadoConPromocion.factorPuntoDeventa(100) shouldBe 1.0
            supermercadoConPromocion.promocion = PromocionCompraSuperiorA200
            supermercadoConPromocion.factorPuntoDeventa(201) shouldBe 0.55
        }
        it("testeo de añadir y quitar descuento") {
            supermercadoConPromocion.promocion = PromocionCombinatorio()
            PromocionCombinatorio().aniadirDescuento(NoHayPromocion)
            PromocionCombinatorio().quitarDescuento(NoHayPromocion)
            PromocionCombinatorio().listaDeDescuentos.size shouldBe 0
        }
    }
    describe("promocion los jueves"){
        it("prueba de promocion los jueves") {
            val descuentoJuevesFalso = stubDiaJueves(false)
            val descuentoJuevesVerdadero = stubDiaJueves(true)
            supermercadoConPromocion.promocion = descuentoJuevesFalso
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 1) shouldBe 1170.0
            supermercadoConPromocion.promocion = descuentoJuevesVerdadero
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 1) shouldBe 1053.0
        }
    }
    describe("promocion del 1 al 10"){
        it("prueba de test de 1 a 10"){
            val descuento1a10Falso = stubPrimerosDíasDelMes(false)
            val descuento1a10Verdadero = stubPrimerosDíasDelMes(true)
            supermercadoConPromocion.promocion = descuento1a10Falso
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 1) shouldBe 1170.0
            supermercadoConPromocion.promocion = descuento1a10Verdadero
            supermercadoConPromocion.importeACobrar(usuarioMayorEdad, 1) shouldBe 1111.5
        }
        it("lado negativo de promocion 1 a 10 "){
            supermercadoConPromocion.promocion
        }
    }



})

