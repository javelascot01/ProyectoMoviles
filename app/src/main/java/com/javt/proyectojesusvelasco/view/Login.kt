package com.javt.proyectojesusvelasco.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityLoginBinding
import com.javt.proyectojesusvelasco.model.Usuario
import com.javt.proyectojesusvelasco.viewModel.RutasSenderismoViewModel
import com.javt.proyectojesusvelasco.viewModel.UsuarioViewModel

class Login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val viewModel: UsuarioViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener(){
            val nombreUsuario=binding.etUserName.text.toString()
            val contrasena=binding.etPassword.text.toString()
            Log.d("Login", "Nombre de usuario: $nombreUsuario, Contraseña: $contrasena")
            viewModel.loginUsuario(nombreUsuario,contrasena){loginExitoso, mensaje->
                if(loginExitoso){
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, PantallaPrincipal::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}