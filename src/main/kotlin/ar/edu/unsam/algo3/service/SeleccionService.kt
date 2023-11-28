package ar.edu.unsam.algo3.service

import BusinessException
import Seleccion
import ar.edu.unsam.algo3.repository.RepoJugador
import ar.edu.unsam.algo3.repository.RepoSeleccion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SeleccionService {

    @Autowired
    lateinit var repoSeleccion: RepoSeleccion

    @Autowired
    lateinit var jugadorRepo: RepoJugador

    fun getSelecciones(campoDeBusqueda: String?) = repoSeleccion.searchByName(campoDeBusqueda)
    fun getSeleccion(id: Int) = repoSeleccion.getById(id)

    fun updateSeleccion(seleccion: Seleccion) = repoSeleccion.update(seleccion)

    fun create(nuevaSeleccion: Seleccion): Seleccion { repoSeleccion.create(nuevaSeleccion)
        return nuevaSeleccion
    }

    fun getSeleccionesFiltrado(nombreABuscar: String) = repoSeleccion.search(nombreABuscar)

    fun deleteSeleccion(id: Int) {
        val seleccion = repoSeleccion.getById(id)
        if(jugadorRepo.allInstances().map { it.seleccion }.contains(seleccion)){
            throw BusinessException("La selección está asociada a un jugador, y no puede ser eliminada")
        } else {
            repoSeleccion.delete(seleccion)
        }
    }

    //fun allInstance() = repoSeleccion.allInstances()


}