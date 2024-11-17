package com.javt.proyectojesusvelasco.view

import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.javt.proyectojesusvelasco.databinding.ActivityPantallaSenderismoBinding
import com.javt.proyectojesusvelasco.viewModel.RutasSenderismoViewModel

class PantallaSenderismo : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaSenderismoBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaSenderismoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel : RutasSenderismoViewModel by viewModels()
        val rutas = viewModel.obtenerRutas()

        if (rutas.isEmpty()) {
            // Si no hay rutas muestra un mensaje
            Toast.makeText(this, "No hay rutas disponibles", Toast.LENGTH_SHORT).show()
        } else {
            // Inicializamos el TabLayout y el ViewPager2
            tabLayout = binding.tabLayout
            viewPager2 = binding.viewPager2
            // Configuramos el adaptador para el ViewPager2
            viewPager2.adapter = PagerAdapter(this, rutas)

            // Configuramos el TabLayout con el ViewPager2
            TabLayoutMediator(tabLayout, viewPager2) { tab, index ->
                // Asignamos dinámicamente el nombre de la ruta en cada tab
                tab.text = rutas[index].nombre
            }.attach()
        }


    }
}