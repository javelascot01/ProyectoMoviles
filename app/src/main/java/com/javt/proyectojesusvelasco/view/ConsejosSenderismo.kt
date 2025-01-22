package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityConsejosSenderismoBinding
import com.javt.proyectojesusvelasco.view.adapter.ConsejoAdapter

// Esta es la actividad que muestra la pantalla "Consejos Senderismo" en la aplicación
class ConsejosSenderismo : AppCompatActivity() {
    private lateinit var binding: ActivityConsejosSenderismoBinding
    private lateinit var consejoAdapter: ConsejoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsejosSenderismoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtengo los consejos del intent
        val consejos = intent.getStringArrayListExtra("consejos")?.toMutableList() ?: mutableListOf()
        val imagenes = listOf(
            R.drawable.ic_water,
            R.drawable.ic_footwear,
            R.drawable.ic_weather
        )

        // Configura el Adapter
        consejoAdapter = ConsejoAdapter(consejos, imagenes)

        // Inicializa el RecyclerView y configura su LayoutManager
        binding.recyclerViewConsejos?.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewConsejos?.adapter = consejoAdapter
    }
}
