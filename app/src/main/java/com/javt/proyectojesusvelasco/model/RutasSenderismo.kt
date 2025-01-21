package com.javt.proyectojesusvelasco.model

import java.io.Serializable

data class RutasSenderismo(
    var id: String? = null,
    var nombre: String = "",
    var descripcion: String = "",
    var distancia: String = "",
    var dificultad: Dificultad = Dificultad.FACIL,
    var duracion: Int = 0
) : Serializable
