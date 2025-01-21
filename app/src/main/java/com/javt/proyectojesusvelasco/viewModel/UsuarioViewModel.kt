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

    fun agregarUsuario(usuario: Usuario) {
        repository.addUsuario(usuario)
    }
    fun loginUsuario(nombreUsuario: String, contrasena: String, onResult: (Boolean, String?) -> Unit) {
        Log.d("Login", "ViewModel")
        repository.loginUsuario(nombreUsuario, contrasena, onResult)
    }
}