package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityConsejosSenderismoBinding

// Esta es la actividad que muestra la pantalla "Consejos Senderismo" en la aplicación
class ConsejosSenderismo : AppCompatActivity() {
    private lateinit var binding: ActivityConsejosSenderismoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityConsejosSenderismoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtengo los consejos del intent
        val consejos = intent.getStringArrayListExtra("consejos")
        if (consejos != null) {
            //Introduzco los consejos obtenidos del intent mediante un separador de string
            binding.txtConsejos.text = consejos.joinToString("\n")
        }
        binding.btnVolver.setOnClickListener {
            finish() // Cierra la actividad actual y regresa a la anterior
        }
    }
}