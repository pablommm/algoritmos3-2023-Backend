package ar.edu.unsam.algo3.bootstrap

import Confederacion
import Delantero
import Desprendido
import Seleccion
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import Direccion
import Figurita
import Jugador
import Kiosco
import Pedido
import Usuario
import ar.edu.unsam.algo3.repository.*

@Service
class WorldCappBootstrap: InitializingBean {

    @Autowired(required=true)
    lateinit var repoSelecciones: RepoSeleccion
    final val argentina = Seleccion("Argentina",Confederacion.CONCACAF,1,1)
    final val brasil = Seleccion("brasil",Confederacion.CONCACAF,1,1)
    final val alemania = Seleccion("alemania",Confederacion.CONCACAF,1,1)
    final val francia = Seleccion("francia",Confederacion.CONCACAF,1,1)

    val seleciones = listOf(argentina,brasil,alemania,francia)

    fun crearSeleciones(){
        seleciones.forEach { repoSelecciones.create(it) }
    }

    override fun afterPropertiesSet() {
        crearSeleciones()
        crearRepoPuntoDeVenta()
        crearJugador()
        crearFiguritas()
        agregarFiguritasUsuario()
        crearUser()
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

    @Autowired(required=true)
    lateinit var jugadorRepo : RepoJugador
    val jugadorLeyenda =  Jugador("Leo","Messi",LocalDate.now().minusYears(30),7,argentina,LocalDate.now().minusYears(14),187.0,83.0, Delantero,false,"Argentina",21000000)
    val jugadorPromesa =  Jugador("Alejandro","Garnacho",LocalDate.now().minusYears(18),11,argentina,LocalDate.now().minusYears(14),187.0,83.0, Delantero,false,"Argentina",21000000)

    val jugadores = listOf(jugadorLeyenda,jugadorPromesa)
    fun crearJugador() {
        jugadores.forEach { jugadorRepo.create(it)}
    }

    @Autowired(required=true)
    lateinit var figuritaRepo: RepoFigurita
    val figuritaLeyenda =Figurita(10, nivelDeImpresion = NivelDeImpresion.ALTA,false,jugadorLeyenda)
    val figuritaPromesa =Figurita(11, nivelDeImpresion = NivelDeImpresion.BAJA,false,jugadorPromesa)

    val figuritas = listOf(figuritaLeyenda,figuritaPromesa)

    fun crearFiguritas(){
        figuritas.forEach { figuritaRepo.create(it) }
    }

    @Autowired(required = true)
    lateinit var repoUser : RepoUser
    val figuritasFaltantes = mutableListOf<Figurita>()
    val figuritasRepetidas = mutableListOf<Figurita>()
    val figuritasFaltantes2 = mutableListOf<Figurita>()
    val figuritasRepetidas2 = mutableListOf<Figurita>()



    val provinciasYLocalidades = mapOf(
        "Buenos Aires" to listOf("La Plata", "Mar del Plata", "Bahía Blanca"),
        "Catamarca" to listOf("San Fernando del Valle de Catamarca", "Andalgalá", "Belén"),
        "Chaco" to listOf("Resistencia", "Barranqueras", "Villa Ángela"),
        "Chubut" to listOf("Rawson", "Comodoro Rivadavia", "Trelew"),
        "Córdoba" to listOf("Córdoba", "Villa María", "Río Cuarto"),
        "Corrientes" to listOf("Corrientes", "Goya", "Mercedes"),

    )

    val DireccionSanMartin =
        Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)

    val usuarioPrueba = Usuario(
        name = "Jose",
        apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        figuritasFaltantes,
        figuritasRepetidas,
        Desprendido,
        jugadorLeyenda,
        20
    )

    val usuarioPrueba2 = Usuario(
        name = "Jose",
        apellido = "Martinez",
        username = "JMartinez",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "JMartinez@gmail.com",
        DireccionSanMartin,
        figuritasFaltantes2,
        figuritasRepetidas2,
        Desprendido,
        jugadorLeyenda,
        20
    )

    val listaUsuario = listOf (
        "nacionalista",
        "Conservador",
        "Fanatico",
        "Desprendido",
        "Apostador",
        "Interesado",
        "Conservador")


    fun agregarFiguritasUsuario(){
        //usuarioPrueba.aniadirFiguritaRepetidas(figuritaLeyenda)
        //usuarioPrueba.aniadirFiguritaFaltantes(figuritaPromesa)
        //usuarioPrueba2.aniadirFiguritaRepetidas(figuritaPromesa)
        //usuarioPrueba2.aniadirFiguritaFaltantes(figuritaLeyenda)
        figuritasFaltantes.add(figuritaLeyenda) // Falta Messi
        figuritasRepetidas.add(figuritaPromesa) // Garnacho Repetido
        figuritasFaltantes2.add(figuritaPromesa) // Falta Garnacho
        figuritasRepetidas2.add(figuritaLeyenda) // Messi Repetido
    }

    fun crearUser(){

        repoUser.create(usuarioPrueba)
        repoUser.create(usuarioPrueba2)
    }



}
