package com.javt.proyectojesusvelasco.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javt.proyectojesusvelasco.model.RutasSenderismo
import com.javt.proyectojesusvelasco.persistence.Repository
import kotlinx.coroutines.launch

class RutasSenderismoViewModel : ViewModel() {
    private val _rutas = MutableLiveData<List<RutasSenderismo>>()
    private val repository = Repository()
    val rutas: MutableLiveData<List<RutasSenderismo>> = repository.getRutas()

    init {
        fetchRutasFromRepository()
    }

    private fun fetchRutasFromRepository() {
        viewModelScope.launch {
            repository.getRutas().observeForever { rutasList ->
                if (rutasList != null) {
                    _rutas.postValue(rutasList)
                    Log.d("RutasRecibidas", "Rutas recibidas: ${_rutas.value}")
                }
            }
        }
    }

    fun obtenerRutas(): List<RutasSenderismo> {
        return _rutas.value.orEmpty()
    }

    fun agregarRuta(ruta: RutasSenderismo) {
        val nuevaLista = _rutas.value?.toMutableList() ?: mutableListOf()
        nuevaLista.add(ruta)
        _rutas.value = nuevaLista
        repository.addRuta(ruta)
    }
}