package ar.edu.unsam.algo3.bootstrap

import Arquero
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
import Libreria
import Mediocampista
import Pedido
import Supermercado
import Usuario
import ar.edu.unsam.algo3.repository.*

@Service
class WorldCappBootstrap: InitializingBean {

    @Autowired(required=true)
    lateinit var repoSelecciones: RepoSeleccion
    final val argentina = Seleccion("Argentina",Confederacion.CONCACAF,3,2)
    final val brasil = Seleccion("brasil",Confederacion.CONCACAF,5,4)
    final val alemania = Seleccion("alemania",Confederacion.CONCACAF,4,1)
    final val francia = Seleccion("francia",Confederacion.CONCACAF,2,2)

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
        org.uqbar.geodds.Point(-34.66128, -58.36713),
        "Avellaneda",
        "Buenos Aires",
        "Av Mitre",
        750
    )

    val direccionBelgrano = Direccion(
        org.uqbar.geodds.Point(-32.66128, -55.36713),
        "Belgrano",
        "Buenos Aires",
        "Av Figueroa Alcorta",
        912
    )

    val direccionPuppo = Direccion(
        org.uqbar.geodds.Point(-34.57291, -58.54999),
        "Buenos Aires",
        "San Martin",
        "Güemes",
        2879
    )

    val listadepedidos = mutableListOf<Pedido>()
    val kioscoJuanito = Kiosco(true, true, 820, "Juanito", direccionAvellaneda, 5, pedido, listadepedidos)
    val libreriaPedro = Libreria(5000, "Pedrito", direccionPuppo, 7, pedido, listadepedidos)
    val kioscoEnzito = Kiosco(true, true, 5000, "Enzito", direccionBelgrano, 9, pedido, listadepedidos)
    val kiosco912 = Kiosco(true, true, 590, "912", direccionAvellaneda, 5, pedido, listadepedidos)

    fun crearRepoPuntoDeVenta(){
        repoPuntoDeVentas.create(kioscoJuanito)
        repoPuntoDeVentas.create(libreriaPedro)
        repoPuntoDeVentas.create(kioscoEnzito)
        repoPuntoDeVentas.create(kiosco912)
    }

    @Autowired(required=true)
    lateinit var jugadorRepo : RepoJugador
    val jugadorLeyenda =  Jugador("Leo","Messi",LocalDate.now().minusYears(30),10,argentina,LocalDate.now().minusYears(14),187.0,83.0, Delantero,true,"Argentina",21000000)
    val jugadorPromesa =  Jugador("Alejandro","Garnacho",LocalDate.now().minusYears(18),11,argentina,LocalDate.now().minusYears(1),187.0,83.0, Delantero,false,"Argentina",1000000)
    val jugadorEnzo =  Jugador("Enzo","Fernández",LocalDate.now().minusYears(18),17,argentina,LocalDate.now().minusYears(14),187.0,83.0, Mediocampista,false,"Argentina",450000)
    val jugadorDalessandro =  Jugador("Andrés","D'Alessandro",LocalDate.now().minusYears(18),14,argentina,LocalDate.now().minusYears(14),187.0,83.0, Mediocampista,false,"Argentina",320000)
    val jugadorDibu =  Jugador("Emiliano","Martinez",LocalDate.now().minusYears(18),19,argentina,LocalDate.now().minusYears(14),187.0,83.0, Arquero,false,"Argentina",100000)
    val jugadorJulian =  Jugador("Julián","Álvarez",LocalDate.now().minusYears(18),20,argentina,LocalDate.now().minusYears(14),187.0,83.0, Delantero,false,"Argentina",570000)


    val jugadores = listOf(jugadorLeyenda,jugadorPromesa)
    fun crearJugador() {
        jugadores.forEach { jugadorRepo.create(it)}
    }

    @Autowired(required=true)
    lateinit var figuritaRepo: RepoFigurita
    val figuritaLeyenda =Figurita(1, nivelDeImpresion = NivelDeImpresion.ALTA,true,jugadorLeyenda, imagen = "https://shorturl.at/gix79")
    val figuritaPromesa =Figurita(2, nivelDeImpresion = NivelDeImpresion.BAJA,false,jugadorPromesa, imagen = "https://shorturl.at/fhpG6")
    val figuritaEnzo =Figurita(3, nivelDeImpresion = NivelDeImpresion.BAJA,false,jugadorEnzo, imagen = "https://shorturl.at/rKLO8")
    val figuritaDalessandro =Figurita(4, nivelDeImpresion = NivelDeImpresion.BAJA,true,jugadorDalessandro, imagen = "https://shorturl.at/oGR24")
    val figuritaDibu =Figurita(5, nivelDeImpresion = NivelDeImpresion.BAJA,false,jugadorDibu, imagen = "https://shorturl.at/npISZ")
    val figuritaJulian =Figurita(6, nivelDeImpresion = NivelDeImpresion.BAJA,false,jugadorJulian, imagen = "https://shorturl.at/rszX2")

    val figuritas = listOf(figuritaLeyenda,figuritaPromesa, figuritaEnzo, figuritaDalessandro, figuritaDibu, figuritaJulian)

    fun crearFiguritas(){
        figuritas.forEach { figuritaRepo.create(it) }
    }

    @Autowired(required = true)
    lateinit var repoUser : RepoUser
    lateinit var repoFigurita: RepoFigurita
    val figuritasFaltantes = mutableListOf<Figurita>()
    val figuritasRepetidas = mutableListOf<Figurita>()
    val figuritasFaltantes2 = mutableListOf<Figurita>()
    val figuritasRepetidas2 = mutableListOf<Figurita>()

    @Autowired(required = true)
    lateinit var repositorioGeneral: RepositorioGeneral

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
        20,
        password = "1234"
    )

    val usuarioPrueba2 = Usuario(
        name = "Frankie",
        apellido = "Stein",
        username = "Frankie123",
        fechaDeNacimiento = LocalDate.now().minusYears(30),
        "FStein@gmail.com",
        DireccionSanMartin,
        figuritasFaltantes2,
        figuritasRepetidas2,
        Desprendido,
        jugadorLeyenda,
        20,
        password = "1234"
    )

    fun agregarFiguritasUsuario(){
        //usuarioPrueba.aniadirFiguritaRepetidas(figuritaLeyenda)
        //usuarioPrueba.aniadirFiguritaFaltantes(figuritaPromesa)
        //usuarioPrueba2.aniadirFiguritaRepetidas(figuritaPromesa)
        //usuarioPrueba2.aniadirFiguritaFaltantes(figuritaLeyenda)
        figuritasFaltantes.add(figuritaLeyenda) // Falta Messi
        figuritasRepetidas.add(figuritaPromesa) // Garnacho Repetido
        figuritasRepetidas.add(figuritaEnzo)
        figuritasRepetidas.add(figuritaJulian)
        figuritasRepetidas.add(figuritaDibu)
        figuritasRepetidas.add(figuritaDalessandro)
        figuritasFaltantes2.add(figuritaPromesa) // Falta Garnacho
        figuritasRepetidas2.add(figuritaLeyenda) // Messi Repetido
    }

    fun crearUser(){
        repoUser.create(usuarioPrueba)
        repoUser.create(usuarioPrueba2)
    }
}
