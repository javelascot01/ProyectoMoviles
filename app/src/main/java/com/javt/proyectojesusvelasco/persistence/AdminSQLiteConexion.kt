package com.javt.proyectojesusvelasco.persistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class AdminSQLiteConexion (context: Context, name:String, factory:SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {
        companion object {
            val DATABASE_VERSION: Int=1
            val DATABASE_NAME: String="administracion.db3"
        }
    constructor(context: Context):
            this(context, DATABASE_NAME, null, DATABASE_VERSION)
    override fun onCreate(db: SQLiteDatabase?) {
        Log.e("SQLLite","Paso por onCreate del AdminSQLIteConexion")
        db?.execSQL("create table persona(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL, descripcion TEXT NOT NULL, distancia TEXT NOT NULL, dificultad TEXT NOT NULL, duracion INTEGER NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.e("SQLLite","Paso por onUpgrade del AdminSQLIteConexion")
    }
}