package ar.edu.unsam.algo3.dto

import Direccion
import PuntoDeVentas
import Usuario
import org.uqbar.geodds.Point




data class PuntoDeVentasDTO(val id: Int, val nombre: String, val direccion: String, val ubicacionGeograficaX: Double, val ubicacionGeograficaY: Double, val distancia: Double, val stockDeSobres: Int, val importeACobrar: Double, val tipo: String)

fun PuntoDeVentas.toDTO(usuario : Usuario) = PuntoDeVentasDTO(id = id, nombre = nombre, direccion = direccion(), ubicacionGeograficaX = ubicacion.obtenerCoordenadaX(), ubicacionGeograficaY = ubicacion.obtenerCoordenadaY(), distancia = cantidadDeKM(usuario), stockDeSobres = stockDeSobres, importeACobrar = importeACobrar(usuario, 1), tipo = tipo())



data class PuntosDeVentaDTOUserless(val id: Int, val nombre: String, val direccion: String, val ubicacionGeograficaX: Double, val ubicacionGeograficaY: Double, val stockDeSobres: Int, val tipo: String, val pedidosPendientes: Int)

fun PuntoDeVentas.toDTOUserless() = PuntosDeVentaDTOUserless(id = id, nombre = nombre, direccion = direccion(), ubicacionGeograficaX = ubicacion.obtenerCoordenadaX(), ubicacionGeograficaY = ubicacion.obtenerCoordenadaY(), stockDeSobres = stockDeSobres, tipo = tipo(), pedidosPendientes = cantidadPedidosPendientes())


data class CreatePuntosDeVentaDTOUserless(val id: Int, val nombre: String, val direccion: String, val ubicacionGeograficaX: Double, val ubicacionGeograficaY: Double, val stockDeSobres: Int, val tipo: String, val pedidosPendientes: Int)