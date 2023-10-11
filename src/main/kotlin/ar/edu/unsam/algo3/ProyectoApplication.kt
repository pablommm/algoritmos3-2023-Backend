package ar.edu.unsam.algo3

import Desprendido
import Direccion
import Figurita
import Kiosco
import Libreria
import NoHayPromocion
import Pedido
import Promocion
import PromocionDel1Hasta10
import Supermercado
import Usuario
import ar.edu.unsam.algo3.repository.RepoPuntoDeVentas
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans
import edu.*
import org.springframework.context.annotation.Bean
import java.time.LocalDate


@SpringBootApplication
@ComponentScans(
    ComponentScan("edu")
)
class ProyectoApplication{
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

    @Bean
    fun getPuntoDeVentas() = RepoPuntoDeVentas().apply {
        create(kioscoJuanito)
    }
}


fun main(args: Array<String>) {
    runApplication<ProyectoApplication>(*args)
}
