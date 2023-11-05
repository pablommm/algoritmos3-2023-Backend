package ar.edu.unsam.algo3.service

import Figurita
import ar.edu.unsam.algo3.dto.*
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

    lateinit var figuritas: List<FiguritaUsuarioDTO>
    fun getFiguritaFiltrado(nombreABuscar: String) = figuritaRepository.search(nombreABuscar)

    fun getFigurines() :List<Figurita> = figuritaRepository.allInstances()

    fun getFiguritasRepetidas(idUsuario: Int, campoDeBusqueda: String?, desde: Int?, hasta: Int?,
                              esPromesa: Boolean?, esOnFire: Boolean?): List<FiguritaUsuarioDTO> {
        figuritasRepetidas(idUsuario)
        filtrarBusqueda(campoDeBusqueda)
        filtrarDesde(desde)
        filtrarHasta(hasta)
        filtrarPromesa(esPromesa)
        filtrarOnFire(esOnFire)
        return this.figuritas
    }

    fun getFiguritasById(idFigurita: Int) = getFiguritasUsuarioDTO(idFigurita).filter { it.figurita.id == idFigurita }.first()

    private fun figuritasRepetidas(idUsuario: Int) {
        this.figuritas = getFiguritasUsuarioDTO(idUsuario).filter { it.idUsuario != idUsuario }
    }

    private fun filtrarBusqueda(campoDeBusqueda: String?) {
        if (campoDeBusqueda === null || campoDeBusqueda === ""){
        } else {
            val campoBusqueda = campoDeBusqueda.uppercase()
            this.figuritas = this.figuritas.filter { it.figurita.nombre.uppercase().contains(campoBusqueda) || it.figurita.apellido.uppercase().contains(campoBusqueda)}
        }
    }

    private fun filtrarDesde(desde: Int?){
        if(desde == null || desde == 0){
        } else {
            this.figuritas = this.figuritas.filter { it.figurita.valoracionFigurita >= desde!!}
        }
    //&& (hasta === null || hasta === 0)
    }

    private fun filtrarHasta(hasta: Int?){
        if(hasta == null || hasta == 0){
        } else {
            this.figuritas = this.figuritas.filter {it.figurita.valoracionFigurita <= hasta!! }
        }
    }

    private fun filtrarPromesa(esPromesa: Boolean?) {
        if((esPromesa == null || esPromesa == false)){
        } else {
            this.figuritas = this.figuritas.filter{ it.figurita.esJugadorPromesa }
        }
    }

    private fun filtrarOnFire(esOnFire: Boolean?) {
        if((esOnFire == null || esOnFire == false)){
        } else {
            this.figuritas = this.figuritas.filter{ it.figurita.onFire }
        }
    }

    fun getFiguritasFaltantesUsuario(idUsuario : Int) = getFiguritasUsuarioDTO(idUsuario).filter { it.idUsuario == idUsuario }

    fun getFiguritasRepetidasUsuario(idUsuario : Int) = getFiguritasUsuarioDTO(idUsuario).filter { it.idUsuario == idUsuario }

    private fun getFiguritasUsuarioDTO(idBusqueda: Int) = usuarioRepository.allInstances().flatMap { usuario ->
        usuario.figuritasRepetidas.map { figurita -> figurita.toDTOFigurita(usuario, figurita) }}
}