package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityPantallaCiclismoBinding

// Esta es la actividad que muestra la pantalla "Ciclismo" en la aplicación
class PantallaCiclismo : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaCiclismoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPantallaCiclismoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Botón de volver
        binding.btnVolver.setOnClickListener {
            finish();
        }
    }
}