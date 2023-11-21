package ar.edu.unsam.algo3.service

import Entidad
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoUser
import ar.edu.unsam.algo3.repository.Repositorio
import ar.edu.unsam.algo3.repository.RepositorioGeneral
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepositorioService {

    @Autowired
    lateinit var repositorio: RepositorioGeneral
    fun getCantidadesHome() = repositorio.toDTO()
}