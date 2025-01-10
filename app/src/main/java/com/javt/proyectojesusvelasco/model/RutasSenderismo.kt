package com.javt.proyectojesusvelasco.model

import java.io.Serializable

data class RutasSenderismo(var id:Int, var nombre:String, var descripcion:String, var distancia:String, var dificultad:Dificultad, var duracion:Int) :
    Serializable
