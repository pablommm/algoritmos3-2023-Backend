package ar.edu.unsam.algo3.dto

import Usuario

data class UsuarioLoginDTO(val user: String, val pass: String)

fun Usuario.toDTO() = UsuarioLoginDTO(user = username, pass = password)

