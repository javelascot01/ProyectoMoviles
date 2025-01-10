package com.javt.proyectojesusvelasco.model

import android.content.Context
import com.javt.proyectojesusvelasco.R

enum class Dificultad(val nivel: Int) {
    FACIL(R.string.dif_facil),
    MEDIA(R.string.dif_moderada),
    DIFICIL(R.string.dif_dificil);

    fun aString(context: Context): String {
        return context.getString(nivel)
    }

}