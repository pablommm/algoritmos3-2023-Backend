package ar.edu.unsam.algo3.bootstrap

import Confederacion
import Seleccion
import ar.edu.unsam.algo3.repository.RepoPuntoDeVentas
import ar.edu.unsam.algo3.repository.RepoSeleccion
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
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

@Service
class WorldCappBootstrap: InitializingBean {

    @Autowired(required=true)
    lateinit var repoSelecciones: RepoSeleccion
    val argentina = Seleccion("Argentina",Confederacion.CONCACAF,1,1)
    val brasil = Seleccion("brasil",Confederacion.CONCACAF,1,1)
    val alemania = Seleccion("alemania",Confederacion.CONCACAF,1,1)
    val francia = Seleccion("francia",Confederacion.CONCACAF,1,1)

    val seleciones = listOf(argentina,brasil,alemania,francia)

    fun crearSeleciones(){
        seleciones.forEach { repoSelecciones.create(it) }
    }

    @Autowired(required=true)
    lateinit var repoPuntoDeVentas: RepoPuntoDeVentas
    val listaDePedido = mutableListOf<Pedido>()

    val pedido = Pedido(100, LocalDate.now())
    val direccionAvellaneda = Direccion(
        org.uqbar.geodds.Point(-34.661289687738716, -58.36713002783264),
        "Avellaneda",
        "Buenos Aires",
        "Av Mitre",
        750
    )

    val listadepedidos = mutableListOf<Pedido>()
    val kioscoJuanito = Kiosco(true, true, 170, "juanito", direccionAvellaneda, 5, pedido, listadepedidos)

    fun crearRepoPuntoDeVenta(){
        repoPuntoDeVentas.create(kioscoJuanito)
    }

    override fun afterPropertiesSet() {
        crearRepoPuntoDeVenta()
    }

}