package com.javt.proyectojesusvelasco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class RutasSenderismoViewModel : ViewModel() {
    private val _rutas = MutableLiveData<MutableList<RutasSenderismo>>(mutableListOf())
    val rutas: LiveData<MutableList<RutasSenderismo>> get() = _rutas

    init {
        _rutas.value = mutableListOf() // Inicializo la lista vacia
    }
    // Funcion para obtener todas las rutas
    fun obtenerRutas(): List<RutasSenderismo> {
        return _rutas.value.orEmpty()
    }

    // Función para agregar una nueva ruta
    fun agregarRuta(ruta: RutasSenderismo) {
        // Añadir la ruta a la lista actual
        val nuevaLista = _rutas.value ?: mutableListOf()
        nuevaLista.add(ruta)
        _rutas.value = nuevaLista
    }
}