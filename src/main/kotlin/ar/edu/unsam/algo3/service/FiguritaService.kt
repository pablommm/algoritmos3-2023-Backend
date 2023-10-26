package ar.edu.unsam.algo3.service

import Figurita
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FiguritaService {

    @Autowired
    lateinit var figuritaRepository: RepoFigurita

    @Autowired
    lateinit var usuarioRepository: RepoUser

    fun getFiguritaFiltrado(nombreABuscar: String) = figuritaRepository.search(nombreABuscar)

    fun getFiguritas() = figuritaRepository.allInstances()

    fun getFiguritasRepetidas(idUsuario: Int) =
        usuarioRepository.allInstancesExcludeId(idUsuario)
            .flatMap { it.figuritasRepetidas }
            .map { it.toDTO() }

    fun getFiguritasFaltantesUsuario(idUsuario : Int) = usuarioRepository.filterById(idUsuario).flatMap { it.figuritasFaltantes }.map { it.toDTO() }

    fun getFiguritasRepetidasUsuario(idUsuario : Int) = usuarioRepository.filterById(idUsuario).flatMap { it.figuritasRepetidas }.map { it.toDTO() }
}