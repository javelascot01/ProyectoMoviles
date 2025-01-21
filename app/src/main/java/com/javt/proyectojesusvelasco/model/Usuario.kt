package com.javt.proyectojesusvelasco.model

import java.io.Serializable

data class Usuario(
    val nombreUsuario: String="",
    val contrasena: String=""
) : Serializable
