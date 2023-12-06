package ar.edu.unsam.algo3.service

import BusinessException
import Figurita
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.RepoFigurita
import ar.edu.unsam.algo3.repository.RepoJugador
import ar.edu.unsam.algo3.repository.RepoUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FiguritaService {

    @Autowired
    lateinit var figuritaRepository: RepoFigurita

    @Autowired
    lateinit var jugadorRepo: RepoJugador

    @Autowired
    lateinit var usuarioRepository: RepoUser

    lateinit var figuritas: List<FiguritaUsuarioDTO>

    lateinit var figuritasUserless: List<Figurita>
    fun getFiguritaFiltrado(nombreABuscar: String) = figuritaRepository.search(nombreABuscar)

    fun getAllFiguritas(campoDeBusqueda: String?):List<Figurita> {
        this.figuritasUserless = figuritaRepository.allInstances()
        filtrarBusquedaUserless(campoDeBusqueda)
        return this.figuritasUserless
    }

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

    private fun filtrarBusquedaUserless(campoDeBusqueda: String?) {
        if (campoDeBusqueda === null || campoDeBusqueda === ""){
        } else {
            val campoBusqueda = campoDeBusqueda.uppercase()
            this.figuritasUserless = this.figuritasUserless.filter { it.nombre().uppercase().contains(campoBusqueda) || it.apellido().uppercase().contains(campoBusqueda)}
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

    fun getFiguritasFaltantesUsuario(idUsuario : Int) = usuarioRepository.allInstances().flatMap { usuario ->
        usuario.figuritasFaltantes.map { figurita -> figurita.toDTOFigurita(usuario, figurita) }}.filter { it.idUsuario == idUsuario }

    fun getFiguritasRepetidasUsuario(idUsuario : Int) = getFiguritasUsuarioDTO(idUsuario).filter { it.idUsuario == idUsuario }

    private fun getFiguritasUsuarioDTO(idBusqueda: Int) = usuarioRepository.allInstances().flatMap { usuario ->
        usuario.figuritasRepetidas.map { figurita -> figurita.toDTOFigurita(usuario, figurita) }}

    fun solicitarFigurita(idUsuarioLogueado: Int, usuarioFigurita: FiguritaUsuarioDTO) {
        val usuarioLogueado = usuarioRepository.getById(idUsuarioLogueado)
        val usuarioASolicitar = usuarioRepository.getById(usuarioFigurita.idUsuario)
        val figurita = figuritaRepository.getById(usuarioFigurita.figurita.id)
        usuarioLogueado.solicitar(figurita, usuarioASolicitar)
    }

    fun quitarFiguritaRepetida(idUsuarioLogueado: Int, idFigurita: Int) {
        val usuarioLogueado = usuarioRepository.getById(idUsuarioLogueado)
        val figuritaASacar = figuritaRepository.getById(idFigurita)
        usuarioLogueado.quitarFiguritaRepetidas(figuritaASacar)
    }

    fun quitarFiguritaFaltante(idUsuarioLogueado: Int, idFigurita: Int) {
        val usuarioLogueado = usuarioRepository.getById(idUsuarioLogueado)
        val figuritaASacar = figuritaRepository.getById(idFigurita)
        usuarioLogueado.quitarFiguritaFaltantes(figuritaASacar)
    }

    fun deleteFigurita(id: Int) {
        val figurita = figuritaRepository.getById(id)
        if(usuarioRepository.allInstances().flatMap { usuario -> usuario.figuritasFaltantes}.contains(figurita)
            || usuarioRepository.allInstances().flatMap { usuario -> usuario.figuritasRepetidas}.contains(figurita)){
            throw BusinessException("La figurita pertenece a un usuario, y no puede ser eliminada")
        } else {
            figuritaRepository.delete(figurita)
        }
    }

    fun dtoToFigurita(figuritaDTO: CreateFiguritaDTO) : Figurita{
        val figurita = Figurita(
            numero = figuritaDTO.numero,
            jugador = jugadorRepo.getById(figuritaDTO.idJugador),
            onFire = figuritaDTO.onFire,
            nivelDeImpresion = figuritaDTO.nivelDeImpresion,
            imagen = figuritaDTO.imagen
        ).also { it.id = figuritaDTO.id }
        return figurita
    }

    fun create(figuritaDTO: CreateFiguritaDTO) {
        figuritaRepository.create(dtoToFigurita(figuritaDTO))
    }

    fun update(figuritaDTO: CreateFiguritaDTO) {
        val figurita = dtoToFigurita(figuritaDTO)
        if (!figuritaRepository.elementos.any{it.numero == figurita.numero}) {
            figuritaRepository.update(dtoToFigurita(figuritaDTO))
        }
        else {
            throw BusinessException("El número ingresado ya pertenece a otra figurita")
        }
    }

    fun getById(id: Int) = figuritaRepository.getById(id)
}