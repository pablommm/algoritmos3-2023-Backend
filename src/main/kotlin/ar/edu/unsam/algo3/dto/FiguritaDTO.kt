


package ar.edu.unsam.algo3.dto

import Figurita
import NivelDeImpresion
import Usuario
import ar.edu.unsam.algo3.repository.RepoJugador
import ar.edu.unsam.algo3.service.FiguritaService
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

data class FiguritaUsuarioDTO(
    val idUsuario : Int,
    val nombreUsuario:  String,
    val figurita: FiguritaDTO
)

fun Figurita.toDTOFigurita(usuario : Usuario, figurita : Figurita) = FiguritaUsuarioDTO(
    idUsuario = usuario.id,
    nombreUsuario = usuario.nombreCompleto(),
    figurita = figurita.toDTO()
)

data class FiguritaDTO(
    val id : Int,
    val numero : Int,
    val nivelDeImpresion: NivelDeImpresion,
    val onFire: Boolean,
    val imagen: String,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: LocalDate,
    val numeroCamiseta: Int,
    val seleccion: String,
    val copasDelMundo: Int,
    val copasConfederacion: Int,
    val anioDebut: LocalDate,
    val altura: Double,
    val peso: Double,
    val posicion: String,
    val cotizacionJugador: Int,
    val esLider: Boolean,
    val edad: Int,
    val valoracionBase: Double,
    val valoracionFigurita: Double,
    val esJugadorPromesa: Boolean)


fun Figurita.toDTO() = FiguritaDTO(id = id, numero = numero, nivelDeImpresion = nivelDeImpresion, onFire = onFire, imagen = imagen, nombre = nombre(), apellido = apellido(),
                                    fechaNacimiento = fechaNacimiento(), numeroCamiseta = numeroCamiseta(), seleccion = seleccion(), copasDelMundo = copasDelMundo(),
                                    copasConfederacion = copasConfederacion(), anioDebut = anioDebut(), altura = altura(), peso = peso(), posicion = posicion(),
                                    cotizacionJugador = cotizacionJugador(), esLider = esLider(), edad = edad(), valoracionBase = valoracionBase(),
                                    valoracionFigurita = valoracionFigurita(), esJugadorPromesa = esJugadorPromesa())



data class CreateFiguritaDTO(
    val numero: Int,
    val id: Int,
    val onFire: Boolean,
    val nivelDeImpresion: NivelDeImpresion,
    val imagen: String
){}



