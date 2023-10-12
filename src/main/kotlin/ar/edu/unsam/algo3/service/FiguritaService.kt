package ar.edu.unsam.algo3.service

import Figurita
import ar.edu.unsam.algo3.repository.RepoFigurita
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FiguritaService {

    @Autowired
    lateinit var figuritaRepository: RepoFigurita

    fun allInstance() = figuritaRepository.allInstances()
    fun getFigurita(id: Int) = figuritaRepository.getById(id)

    fun deleteFigurita(id: Int) {
        figuritaRepository.delete(figuritaRepository.getById(id))
    }

    fun getFiguritaFiltrado(nombreABuscar: String) = figuritaRepository.search(nombreABuscar)

    fun create(nuevaFigurita: Figurita): Figurita {
        figuritaRepository.create(nuevaFigurita)
        return nuevaFigurita
    }

    fun updateFigurita(figurita: Figurita) = figuritaRepository.update(figurita)
}