package ar.edu.unsam.algo3.service

import Entidad
import ar.edu.unsam.algo3.dto.RepositorioDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepositorioService {

    @Autowired
    lateinit var repoPuntoDeVentas: RepoPuntoDeVentas

    @Autowired
    lateinit var repoUser: RepoUser

    data class RepositorioGeneral(
        val cantidadPuntosDeVentas: Int,
        val cantidadUsuarios: Int,
        val cantidadFiguritasRepetidas: Int,
        val cantidadFiguritasFaltantes: Int
    )
    fun getCantidadesHome() = RepositorioGeneral(cantidadPuntosDeVentas = repoPuntoDeVentas.cantidadElementos(),
        cantidadUsuarios = repoUser.cantidadElementos(),
        cantidadFiguritasRepetidas = repoUser.allInstances().map { it.figuritasRepetidas.size }.sum(),
        cantidadFiguritasFaltantes = repoUser.allInstances().map { it.figuritasFaltantes.size }.sum()
    )
}