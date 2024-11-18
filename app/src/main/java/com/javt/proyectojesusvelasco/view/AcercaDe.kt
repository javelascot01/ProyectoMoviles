package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityAcercaDeBinding
import com.javt.proyectojesusvelasco.databinding.ActivityConsejosSenderismoBinding

class AcercaDe : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaDeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val acercaDeInfo=arrayOf(
            getString(R.string.app_name),
            getString(R.string.app_description),
            getString(R.string.version),
            getString(R.string.developer),
            getString(R.string.contact)
        )
        binding.txtAcercaDe1.text = acercaDeInfo.joinToString("\n")
        binding.btnVolver.setOnClickListener(){
            finish() // Cierra la actividad actual y regresa a la anterior
        }
    }
}