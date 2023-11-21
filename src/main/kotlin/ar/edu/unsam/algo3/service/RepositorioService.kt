package ar.edu.unsam.algo3.service

import Entidad
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.Repositorio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RepositorioService {

    @Autowired
    lateinit var repositorio: Repositorio<Entidad>
    fun getCantidadesHome() = repositorio.toDTO()
}