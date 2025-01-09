package com.javt.proyectojesusvelasco.model

import android.content.Context
import com.javt.proyectojesusvelasco.R

enum class Dificultad(val nivel: Int) {
    FACIL(R.string.dif_facil),
    MEDIA(R.string.dif_moderada),
    DIFICIL(R.string.dif_dificil);

    fun toLocalizedString(context: Context): String {
        return context.getString(nivel)
    }

    companion object {
        fun fromString(context: Context, value: String): Dificultad? {
            return values().find { it.toLocalizedString(context) == value }
        }
    }
}