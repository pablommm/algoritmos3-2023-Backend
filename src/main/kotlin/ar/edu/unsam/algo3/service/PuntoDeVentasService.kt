package ar.edu.unsam.algo3.service

import PuntoDeVentas
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.RepoPuntoDeVentas
import ar.edu.unsam.algo3.repository.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PuntoDeVentasService {

    @Autowired
    lateinit var puntoDeVentasRepository: RepoPuntoDeVentas

    @Autowired
    lateinit var usuarioRepository: RepoUser
    fun getPuntosDeVenta()= puntoDeVentasRepository.allInstances()
    //fun getPuntosDeVenta(campoDeBusqueda: String?): List<PuntosDeVentaDTOUserless> = puntoDeVentasRepository.searchByName(campoDeBusqueda).map { it.toDTOUserless() }

    fun getUnPuntoDeVentas(id: Int) = puntoDeVentasRepository.getById(id)
    fun getPuntoDeVentasFiltrado(nombreABuscar: String?, criterioOrdenamiento: CriterioOrdenamiento?, idUsuario: Int): List<PuntoDeVentasDTO> {
        return this.ordenarPorCriterio(criterioOrdenamiento, nombreABuscar, idUsuario)
    }

    private fun busquedaSearchbar(nombreABuscar: String?): List<PuntoDeVentas> {
        if (nombreABuscar === null || nombreABuscar === "") {
            return puntoDeVentasRepository.allInstances()
        } else {
            return puntoDeVentasRepository.search(nombreABuscar)
        }
    }

    fun orderByAscendingDistancia(idUsuario: Int, nombreABuscar: String?): List<PuntoDeVentasDTO> {
        val usuario = usuarioRepository.getById(idUsuario)
        return this.busquedaSearchbar(nombreABuscar).sortedBy { it.cantidadDeKM(usuario) }.map { it.toDTO(usuario) }
    }

    fun orderByAscendingBarato(usuarioId: Int, nombreABuscar: String?): List<PuntoDeVentasDTO> {
        val usuario = usuarioRepository.getById(usuarioId)
        return this.busquedaSearchbar(nombreABuscar).sortedBy { it.importeACobrar(usuario, 1) }.map { it.toDTO(usuario) }
    }

    fun orderByDescendingSobres(usuarioId: Int, nombreABuscar: String?): List<PuntoDeVentasDTO> {
        val usuario = usuarioRepository.getById(usuarioId)
        return this.busquedaSearchbar(nombreABuscar).sortedByDescending { it.stockDeSobres }.map { it.toDTO(usuario) }
    }

    fun ordenarPorCriterio(criterio: CriterioOrdenamiento?, nombreABuscar: String?, idUsuario: Int): List<PuntoDeVentasDTO> {
        when (criterio) {
            CriterioOrdenamiento.MenorDistancia -> {
                return this.orderByAscendingDistancia(idUsuario, nombreABuscar)
            }
            CriterioOrdenamiento.MasBarato -> {
                return this.orderByAscendingBarato(idUsuario, nombreABuscar)
            }
            CriterioOrdenamiento.MasSobres -> {
                return this.orderByDescendingSobres(idUsuario, nombreABuscar)
            }

            else -> {return this.orderByAscendingDistancia(idUsuario, nombreABuscar)}
        }
    }

    fun deletePuntoDeVenta(id: Int) {
        val puntoDeVenta = puntoDeVentasRepository.getById(id)
        puntoDeVentasRepository.delete(puntoDeVenta)
    }

    fun createpdv(PuntosDeVentaDTOUserless: PuntosDeVentaDTOUserless) {
        val puntoDeVentas = PuntosDeVentaDTOUserless(
            id = PuntosDeVentaDTOUserless.id,
            nombre = PuntosDeVentaDTOUserless.nombre,
            direccion = PuntosDeVentaDTOUserless.direccion,
//            ubicacionGeograficaX = PuntosDeVentaDTOUserless.ubicacionGeograficaX,
//            ubicacionGeograficaY = PuntosDeVentaDTOUserless.ubicacionGeograficaY,
            stockDeSobres = PuntosDeVentaDTOUserless.stockDeSobres,
            tipo = PuntosDeVentaDTOUserless.tipo,
            pedidosPendientes = PuntosDeVentaDTOUserless.pedidosPendientes,
            ubicacionGeograficaX = PuntosDeVentaDTOUserless.ubicacionGeograficaX,
            ubicacionGeograficaY = PuntosDeVentaDTOUserless.ubicacionGeograficaY
        )
        //puntoDeVentasRepository.create(puntoDeVentas)

    }
    fun create(puntoDeVentas: PuntoDeVentas){
        puntoDeVentasRepository.create(puntoDeVentas)
    }

    fun update(puntoDeVentas: PuntoDeVentas){
        puntoDeVentasRepository.update(puntoDeVentas)
    }
}

enum class CriterioOrdenamiento {
    MenorDistancia,
    MasBarato,
    MasSobres,
}
