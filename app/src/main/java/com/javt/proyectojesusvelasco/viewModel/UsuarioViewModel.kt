package com.javt.proyectojesusvelasco.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.javt.proyectojesusvelasco.model.Usuario

class UsuarioViewModel : ViewModel() {

    private val _user = MutableLiveData<Usuario?>()
    val user: LiveData<Usuario?> get() = _user

    fun login(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            _user.value = Usuario(username, password)
        } else {
            _user.value = null
        }
    }

}