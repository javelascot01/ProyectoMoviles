package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityAcercaDeBinding
import com.javt.proyectojesusvelasco.databinding.ActivityConsejosSenderismoBinding
import com.javt.proyectojesusvelasco.model.Dificultad

// Esta es la actividad que muestra la pantalla "Acerca de" en la aplicación
class AcercaDe : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaDeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Creo un array con la información que quiero mostrar en la pantalla
        val acercaDeInfo=arrayOf(
            getString(R.string.app_name),
            getString(R.string.app_description),
            getString(R.string.version),
            getString(R.string.developer),
            getString(R.string.contact)
        )
        //Lo convierto en string separando con saltos de linea
        binding.txtAcercaDe1.text = acercaDeInfo.joinToString("\n")

    }
}