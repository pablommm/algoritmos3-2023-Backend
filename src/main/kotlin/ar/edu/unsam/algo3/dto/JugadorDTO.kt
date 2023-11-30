package ar.edu.unsam.algo3.dto

import Jugador
import Posicion
import Seleccion
import java.time.LocalDate

data class JugadorDTO(val id: Int, val nombre: String, val apellido: String)

fun Jugador.toDTO() = JugadorDTO(id = id, nombre = nombre, apellido = apellido)


