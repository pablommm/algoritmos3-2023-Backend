package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.repository.RepoSeleccion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SeleccionService {
    @Autowired
    lateinit var repoSeleccion: RepoSeleccion

    fun allInstance() = repoSeleccion.allInstances()


}