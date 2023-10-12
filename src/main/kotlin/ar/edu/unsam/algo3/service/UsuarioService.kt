package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.repository.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {

    @Autowired
    lateinit var usuarioService: RepoUser

    fun getUser() = usuarioService.allInstances()

}