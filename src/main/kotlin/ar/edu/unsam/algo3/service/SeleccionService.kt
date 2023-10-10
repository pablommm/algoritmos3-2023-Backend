package ar.edu.unsam.algo3.service

import RepoSeleccion
import Repositorio
import Seleccion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SeleccionService {
    @Autowired(required=true)
    lateinit var repoSeleccion:RepoSeleccion

    fun allInstance() = repoSeleccion.allInstances()


}