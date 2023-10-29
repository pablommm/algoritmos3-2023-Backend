package ar.edu.unsam.algo3.service

import Figurita
import ar.edu.unsam.algo3.dto.FiguritaDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FiguritaService {

    @Autowired
    lateinit var figuritaRepository: RepoFigurita

    @Autowired
    lateinit var usuarioRepository: RepoUser

    lateinit var figuritas: List<Figurita>
    fun getFiguritaFiltrado(nombreABuscar: String) = figuritaRepository.search(nombreABuscar)

    //fun getFiguritas() = figuritaRepository.allInstances()

    fun getFiguritasRepetidas(idUsuario: Int, desde: Int?, hasta: Int?,
                              esPromesa: Boolean?, esOnFire: Boolean?): List<Figurita> {
        figuritasRepetidas(idUsuario)
        filtrarDesde(desde)
        filtrarHasta(hasta)
        filtrarPromesa(esPromesa)
        filtrarOnFire(esOnFire)
        return this.figuritas//.map {it.toDTO()}
    }

    fun getFiguritasById(idFigurita: Int) = figuritaRepository.getById(idFigurita)

    private fun figuritasRepetidas(idUsuario: Int) {
        this.figuritas = usuarioRepository.allInstancesExcludeId(idUsuario)
            .flatMap { it.figuritasRepetidas }
    }

    private fun filtrarDesde(desde: Int?){
        if(desde == null || desde == 0){
        } else {
            this.figuritas = this.figuritas.filter { it.valoracionFigurita()>= desde!!}
        }
    //&& (hasta === null || hasta === 0)
    }

    private fun filtrarHasta(hasta: Int?){
        if(hasta == null || hasta == 0){
        } else {
            this.figuritas = this.figuritas.filter {it.valoracionFigurita()<= hasta!! }
        }
    }

    private fun filtrarPromesa(esPromesa: Boolean?) {
        if((esPromesa == null || esPromesa == false)){
        } else {
            this.figuritas = this.figuritas.filter{ it.esJugadorPromesa() }
        }
    }

    private fun filtrarOnFire(esOnFire: Boolean?) {
        if((esOnFire == null || esOnFire == false)){
        } else {
            this.figuritas = this.figuritas.filter{ it.onFire }
        }
    }

    fun getFiguritasFaltantesUsuario(idUsuario : Int) = usuarioRepository.filterById(idUsuario).flatMap { it.figuritasFaltantes }.map { it.toDTO() }

    fun getFiguritasRepetidasUsuario(idUsuario : Int) = usuarioRepository.filterById(idUsuario).flatMap { it.figuritasRepetidas }.map { it.toDTO() }
}