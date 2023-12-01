package ar.edu.unsam.algo3.dto

import Jugador
import Posicion
import Seleccion
import java.time.LocalDate

data class JugadorDTO(val id: Int, val nombre: String, val apellido: String)

fun Jugador.toDTO() = JugadorDTO(id = id, nombre = nombre, apellido = apellido)


data class CreateJugadorDTO(
    val nombre: String,
    val apellido: String,
    val fechaDeNacimiento: String,
    val nroDeCamiseta: Int,
    val lider: Boolean,
    val anioDebut: String,
    val altura: Double,
    val peso: Double,
    val posicion: String,
    val cotizacion: Int,
    val idSeleccion: Int
){}
