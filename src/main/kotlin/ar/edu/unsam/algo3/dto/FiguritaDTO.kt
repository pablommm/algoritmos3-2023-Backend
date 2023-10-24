package ar.edu.unsam.algo3.dto

import Figurita
import NivelDeImpresion
import ar.edu.unsam.algo3.repository.RepoJugador
import ar.edu.unsam.algo3.service.FiguritaService

data class FiguritaDTO(val nivelDeImpresion: NivelDeImpresion, val onFire:  Boolean, val jugador: JugadorDTO)


fun Figurita.toDTO() = FiguritaDTO(nivelDeImpresion = nivelDeImpresion, onFire = onFire, jugador = jugador.toDTO())

