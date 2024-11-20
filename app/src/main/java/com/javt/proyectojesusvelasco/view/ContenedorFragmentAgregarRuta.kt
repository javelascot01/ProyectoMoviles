package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityContenedorFragmentAgregarRutaBinding

class ContenedorFragmentAgregarRuta : AppCompatActivity() {
    private lateinit var binding : ActivityContenedorFragmentAgregarRutaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContenedorFragmentAgregarRutaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Agregar el fragmento al Contenedor
        val fragmento=AgregarRutaFragment()
        val fragmentTransaction=supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerAgregar, fragmento)
        fragmentTransaction.commit()



    }
}