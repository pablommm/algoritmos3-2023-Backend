package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HomeService {

    @Autowired
    lateinit var repoPuntoDeVentas: RepoPuntoDeVentas

    @Autowired
    lateinit var repoUser: RepoUser

    data class Home(
        val cantidadPuntosDeVentas: Int,
        val cantidadUsuarios: Int,
        val cantidadFiguritasRepetidas: Int,
        val cantidadFiguritasFaltantes: Int
    )
    fun getCantidadesHome() = Home(cantidadPuntosDeVentas = repoPuntoDeVentas.cantidadElementos(),
        cantidadUsuarios = repoUser.cantidadElementos(),
        cantidadFiguritasRepetidas = repoUser.allInstances().map { it.figuritasRepetidas.size }.sum(),
        cantidadFiguritasFaltantes = repoUser.allInstances().map { it.figuritasFaltantes.size }.sum()
    )
}