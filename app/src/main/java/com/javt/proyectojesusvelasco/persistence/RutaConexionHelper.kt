package com.javt.proyectojesusvelasco.persistence

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import com.javt.proyectojesusvelasco.model.RutasSenderismo

object RutaConexionHelper {

    fun addRuta(contexto: AppCompatActivity, r:RutasSenderismo):Long{
        val admin=AdminSQLiteConexion(contexto)
        val bd=admin.writableDatabase
        val registro= ContentValues()
        registro.put("nombre",r.nombre)
        registro.put("descripcion",r.descripcion)
        registro.put("distancia",r.distancia)
        registro.put("dificultad",r.dificultad)
        registro.put("duracion",r.duracion)
        val id=bd.insert("rutas",null,registro)
        bd.close()
        return id
    }
    fun delRuta(contexto: AppCompatActivity, id:Int):Int{
        val admin=AdminSQLiteConexion(contexto)
        val bd=admin.writableDatabase
        val cant=bd.delete("rutas","id=?", arrayOf(id.toString()))
        bd.close()
        return cant
    }
    fun updRuta(contexto: AppCompatActivity, r:RutasSenderismo):Int{
        val admin=AdminSQLiteConexion(contexto)
        val bd=admin.writableDatabase
        val registro= ContentValues()
        registro.put("nombre",r.nombre)
        registro.put("descripcion",r.descripcion)
        registro.put("distancia",r.distancia)
        registro.put("dificultad",r.dificultad)
        registro.put("duracion",r.duracion)
        val cant=bd.update("rutas",registro,"id=?", arrayOf(r.id.toString()))
        bd.close()
        return cant
    }
    fun getRuta(contexto: AppCompatActivity, id:Int):RutasSenderismo{
        val admin=AdminSQLiteConexion(contexto)
        val bd=admin.writableDatabase
        val fila=bd.rawQuery("select nombre,descripcion,distancia,dificultad,duracion from rutas where id=?", arrayOf(id.toString()))
        if(fila.moveToFirst()){
            val nombre=fila.getString(0)
            val descripcion=fila.getString(1)
            val distancia=fila.getString(2)
            val dificultad=fila.getString(3)
            val duracion=fila.getInt(4)
            return RutasSenderismo(id,nombre,descripcion,distancia,dificultad,duracion)
        }
        return RutasSenderismo(0,"","","","",0)

    }
}