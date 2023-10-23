package ar.edu.unsam.algo3.service

import PuntoDeVentas
import ar.edu.unsam.algo3.repository.RepoPuntoDeVentas
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PuntoDeVentasService {

    @Autowired
    lateinit var puntoDeVentasRepository: RepoPuntoDeVentas

    fun getPuntoDeVentas() = puntoDeVentasRepository.allInstances()
    fun getPuntoDeVentasFiltrado(nombreABuscar: String) = puntoDeVentasRepository.search(nombreABuscar)

}