package ar.edu.unsam.algo3.dto

import Usuario

data class UsuarioDTO(val id: Int, val name: String)

fun Usuario.toDTO() = UsuarioDTO(id = id, name = name)

