package com.javt.proyectojesusvelasco.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javt.proyectojesusvelasco.model.RutasSenderismo
import com.javt.proyectojesusvelasco.model.Usuario
import com.javt.proyectojesusvelasco.persistence.Repository

class UsuarioViewModel : ViewModel() {

    private val repository = Repository()

    // Método que agrega un usuario al repositorio
    fun agregarUsuario(usuario: Usuario) {
        repository.addUsuario(usuario)
    }
    // Método que comprueba si un usuario está registrado
    fun loginUsuario(nombreUsuario: String, contrasena: String, onResult: (Boolean, String?, String?) -> Unit) {
        Log.d("Login", "ViewModel")
        repository.loginUsuario(nombreUsuario, contrasena, onResult)
    }
    // Método que obtiene un usuario por su id de usuario y llama al repositorio apra que lo cambie de nombre
    fun actualizarNombreUsuario(idUsuario: String, nuevoNombreUsuario: String, onResult: (Boolean) -> Unit) {
        Log.d("UsuarioViewModel", "Actualizando nombre de usuario $nuevoNombreUsuario")

        // Llamada al repositorio para actualizar el nombre de usuario
        repository.actualizarNombreUsuario(idUsuario, nuevoNombreUsuario, onResult)
    }
}