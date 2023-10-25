package ar.edu.unsam.algo3.service

import PuntoDeVentas
import Usuario
import ar.edu.unsam.algo3.repository.RepoPuntoDeVentas
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PuntoDeVentasService {

    @Autowired
    lateinit var puntoDeVentasRepository: RepoPuntoDeVentas

    fun getPuntoDeVentas() = puntoDeVentasRepository.allInstances()
    fun getPuntoDeVentasFiltrado(nombreABuscar: String?, criterioOrdenamiento: String?): List<PuntoDeVentas>{
        return this.orderByDescendingSobres(nombreABuscar)
    //return busquedaSearchbar(nombreABuscar)
    }

    private fun busquedaSearchbar(nombreABuscar: String?): List<PuntoDeVentas> {
        if (nombreABuscar === null || nombreABuscar === "") {
            return puntoDeVentasRepository.allInstances()
        } else {
            return puntoDeVentasRepository.search(nombreABuscar)
        }
    }


    //fun orderByAscendingDistancia(nombreABuscar: String?, usuario: Usuario) {
        //return this.busquedaSearchbar(nombreABuscar).sortedBy { it.cantidadDeKM(usuario) }
    //}

    //fun orderByAscendingBarato() {
        //return this.busquedaSearchbar(nombreABuscar).sortedBy { it.(usuario) }
    //}
    fun orderByDescendingSobres(nombreABuscar: String?): List<PuntoDeVentas> {
        return this.busquedaSearchbar(nombreABuscar).sortedByDescending { it.stockDeSobres }
    }
    /*fun ordenarPorCriterio(criterio: CriterioOrdenamiento, nombreABuscar: String?): List<PuntoDeVentas> {
        when (criterio) {
            CriterioOrdenamiento.MenorDistancia -> {
                //
            }
            CriterioOrdenamiento.MasBarato -> {
                //
            }
            CriterioOrdenamiento.MasSobres -> {
                return this.orderByDescendingSobres(nombreABuscar)
            }
        }
    }*/
}

/*
enum class CriterioOrdenamiento {
    MenorDistancia,
    MasBarato,
    MasSobres,
}*/
