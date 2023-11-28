package ar.edu.unsam.algo3.service

import Seleccion
import ar.edu.unsam.algo3.repository.RepoSeleccion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SeleccionService {

    @Autowired
    lateinit var repoSeleccion: RepoSeleccion

    fun getSelecciones(campoDeBusqueda: String?) = repoSeleccion.searchByName(campoDeBusqueda)
    fun getSeleccion(id: Int) = repoSeleccion.getById(id)

    fun updateSeleccion(seleccion: Seleccion) = repoSeleccion.update(seleccion)

    fun create(nuevaSeleccion: Seleccion): Seleccion { repoSeleccion.create(nuevaSeleccion)
        return nuevaSeleccion
    }

    fun getSeleccionesFiltrado(nombreABuscar: String) = repoSeleccion.search(nombreABuscar)

    fun deleteSeleccion(id: Int) {
        repoSeleccion.delete(repoSeleccion.getById(id))
    }

    //fun allInstance() = repoSeleccion.allInstances()


}