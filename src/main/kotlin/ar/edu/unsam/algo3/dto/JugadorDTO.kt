package ar.edu.unsam.algo3.dto

import Jugador
import Posicion
import Seleccion
import java.time.LocalDate

data class JugadorDTO(val nombre: String, val apellido: String, val fechaDeNacimiento: LocalDate, val nroDeCamiseta: Int, val seleccion: Seleccion, val anioDebut: LocalDate, val altura: Double, val peso: Double, val posicion: Posicion, val lider: Boolean, val cotizacion: Int)

fun Jugador.toDTO() = JugadorDTO(nombre = nombre, apellido = apellido, fechaDeNacimiento = fechaDeNacimiento, nroDeCamiseta = nroDeCamiseta, seleccion = seleccion, anioDebut = anioDebut, altura = altura, peso = peso, posicion = posicion, lider = lider, cotizacion = cotizacion)


