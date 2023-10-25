package ar.edu.unsam.algo3.service

import Usuario
import ar.edu.unsam.algo3.repository.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UsuarioService {

    @Autowired
    lateinit var usuarioService: RepoUser

    fun getUser() = usuarioService.allInstances()
    fun getUser(id: Int) = usuarioService.getById(id)

    fun deleteUser(id: Int) {
        usuarioService.delete(usuarioService.getById(id))
    }

    fun updateUser(usuario: Usuario) = usuarioService.update(usuario)

    fun create(nuevoUsuario: Usuario): Usuario {
        usuarioService.create(nuevoUsuario)
        return nuevoUsuario
    }
    fun getUsuarioLogin(user:String, pass:Int) = usuarioService.getUserPass(user,pass)
    fun getUserFiltrado(nombreABuscar: String) = usuarioService.search(nombreABuscar)

}