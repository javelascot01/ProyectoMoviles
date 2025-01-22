package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityRegisterBinding
import com.javt.proyectojesusvelasco.model.Usuario
import com.javt.proyectojesusvelasco.viewModel.UsuarioViewModel

class Register : AppCompatActivity() {
    // Esta es la actividad que muestra la pantalla de registro en la aplicación
    private lateinit var binding : ActivityRegisterBinding
    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val username=binding.etUserName.text.toString()
            val password=binding.etPassword.text.toString()

            // Si los campos no están vacíos, creo un usuario y lo agrego al ViewModel que accede a base de datos y crea el usuario
            if(username.isNotEmpty() && password.isNotEmpty()){
                val user= Usuario(username,password)
                viewModel.agregarUsuario(user)
                finish()
            }
        }
    }
}