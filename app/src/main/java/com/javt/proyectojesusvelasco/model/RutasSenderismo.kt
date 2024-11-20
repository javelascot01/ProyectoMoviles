package com.javt.proyectojesusvelasco.model

import java.io.Serializable

data class RutasSenderismo(var nombre:String, var descripcion:String, var distancia:String, var dificultad:String, var duracion:Int) :
    Serializable
