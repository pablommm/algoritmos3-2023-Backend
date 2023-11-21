package ar.edu.unsam.algo3.dto

import Entidad
import PuntoDeVentas
import Usuario
import ar.edu.unsam.algo3.repository.RepoPuntoDeVentas
import ar.edu.unsam.algo3.repository.RepoUser
import ar.edu.unsam.algo3.repository.RepositorioGeneral
import ar.edu.unsam.algo3.repository.Repositorio

data class RepositorioDTO(
    val cantidadPuntosDeVentas: Int,
    val cantidadUsuarios: Int,
    val cantidadFiguritasRepetidas: Int,
    val cantidadFiguritasFaltantes: Int
)
fun RepositorioGeneral.toDTO() = RepositorioDTO(cantidadPuntosDeVentas = RepoPuntoDeVentas().cantidadElementos(), cantidadUsuarios = RepoUser().cantidadElementos(),
    cantidadFiguritasRepetidas = RepoUser().allInstances().map { it.figuritasRepetidas.size }.sum(), cantidadFiguritasFaltantes = RepoUser().allInstances().map { it.figuritasFaltantes.size }.sum())


/*data class RepositorioUsuarioDTO(
    val cantidadUsuariosActivos: Int
)
fun Repositorio<Usuario>.toDTO() = RepositorioUsuarioDTO(cantidadUsuariosActivos = Repositorio<Usuario>().cantidadElementos())

data class RepositorioPuntoDeVentasDTO(
    val cantidadPuntosDeVentas: Int
)
fun Repositorio<PuntoDeVentas>.toDTO() = RepositorioPuntoDeVentasDTO(cantidadPuntosDeVentas = Repositorio<PuntoDeVentas>().cantidadElementos())

*/
