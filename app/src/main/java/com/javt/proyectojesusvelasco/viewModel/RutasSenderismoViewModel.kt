package com.javt.proyectojesusvelasco.viewModel

import androidx.lifecycle.ViewModel
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class RutasSenderismoViewModel : ViewModel() {
    private val rutas: MutableList<RutasSenderismo> = mutableListOf()
    // Agregar algunas rutas iniciales de ejemplo
    init {
        rutas.add(RutasSenderismo("Ruta Bosque", "Hermosa ruta en el bosque", "5 km", "Fácil", 120))
        rutas.add(RutasSenderismo("Ruta Montaña", "Desafiante ruta en la montaña", "8 km", "Difícil", 180))
        rutas.add(RutasSenderismo("Ruta Lago", "Ruta alrededor de un lago", "6 km", "Moderada", 150))
    }

    // Función para obtener todas las rutas
    fun obtenerRutas(): List<RutasSenderismo> {
        return rutas
    }

    // Función para agregar una nueva ruta
    fun agregarRuta(ruta: RutasSenderismo) {
        rutas.add(ruta)
    }

    // Función para eliminar una ruta
    fun eliminarRuta(ruta: RutasSenderismo) {
        rutas.remove(ruta)
    }
}