package ar.edu.unsam.algo3.service

import TipoDeUsuario
import Usuario
import ar.edu.unsam.algo3.repository.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {

    @Autowired
    lateinit var repoUsuario: RepoUser

    fun getUser() = repoUsuario.allInstances()
    fun getUser(id: Int) = repoUsuario.getById(id)

    fun deleteUser(id: Int) {
        repoUsuario.delete(repoUsuario.getById(id))
    }

    fun updateUser(usuario: Usuario) = repoUsuario.update(usuario)

    fun create(nuevoUsuario: Usuario): Usuario {
        repoUsuario.create(nuevoUsuario)
        return nuevoUsuario
    }
    fun getUsuarioLogin(user:Usuario) = repoUsuario.getUserPass(user)
    fun getUserFiltrado(nombreABuscar: String) = repoUsuario.search(nombreABuscar)

    //fun getListaTiposUsuarios(criterio: TipoDeUsuario) = repoUsuario.
}