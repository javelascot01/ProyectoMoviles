package com.javt.proyectojesusvelasco

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.javt.proyectojesusvelasco.databinding.PantallaPrincipalBinding

class PantallaPrincipal : AppCompatActivity() {
    private lateinit var binding : PantallaPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}