package ar.edu.unsam.algo3.service

import PuntoDeVentas
import ar.edu.unsam.algo3.repository.Repositorio
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PuntoDeVentasService {

    @Autowired
    lateinit var puntoDeVentasRepository: Repositorio<PuntoDeVentas>

    fun getPuntoDeVentas() = puntoDeVentasRepository.search("")
    fun getPuntoDeVenta(id: Int) = puntoDeVentasRepository.getById(id)

    fun deletePuntoDeVenta(id: Int) {
        puntoDeVentasRepository.delete(puntoDeVentasRepository.getById(id))
    }
    fun updatePuntoDeVenta(puntoDeVentas: PuntoDeVentas) = puntoDeVentasRepository.update(puntoDeVentas)

    fun create(nuevoPuntoDeVentas: PuntoDeVentas): PuntoDeVentas { puntoDeVentasRepository.create(nuevoPuntoDeVentas)
        return nuevoPuntoDeVentas
    }

    fun getPuntoDeVentasFiltrado(nombreABuscar: String) = puntoDeVentasRepository.search(nombreABuscar)
}