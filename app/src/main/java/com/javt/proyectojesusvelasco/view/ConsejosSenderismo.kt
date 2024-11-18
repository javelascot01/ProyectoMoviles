package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityConsejosSenderismoBinding

class ConsejosSenderismo : AppCompatActivity() {
    private lateinit var binding: ActivityConsejosSenderismoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityConsejosSenderismoBinding.inflate(layoutInflater)
        setContentView(binding.root)
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