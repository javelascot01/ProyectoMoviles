package com.javt.proyectojesusvelasco.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityPantallaSenderismoBinding
import com.javt.proyectojesusvelasco.model.RutasSenderismo
import com.javt.proyectojesusvelasco.viewModel.RutasSenderismoViewModel

class PantallaSenderismo : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaSenderismoBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: PagerAdapter

    private val viewModel: RutasSenderismoViewModel by viewModels()
    @Suppress("DEPRECATION")
    private val agregarRutaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val nuevaRuta = result.data?.getSerializableExtra("nuevaRuta") as? RutasSenderismo
            nuevaRuta?.let { viewModel.agregarRuta(it) }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaSenderismoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Inicializar las rutas y agregarlas al ViewModel
        inicializarRutas()

        // Inicializo el TabLayout y ViewPager2
        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager2

        // Configurar el adaptador para el ViewPager2
        adapter = PagerAdapter(this, viewModel.obtenerRutas())
        viewPager2.adapter = adapter

        // Configurar el TabLayout con el ViewPager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, index ->
            tab.text = viewModel.obtenerRutas()[index].nombre
        }.attach()

        viewModel.rutas.observe(this) { rutas ->
            // Actualizar el adaptador cuando las rutas cambian
            adapter = PagerAdapter(this, viewModel.obtenerRutas())
            viewPager2.adapter = adapter
            adapter.updateRutas(rutas)

            // Actualizar los tabs
            TabLayoutMediator(tabLayout, viewPager2) { tab, index ->
                tab.text = rutas[index].nombre
            }.attach()
        }

        // Funcionalidad del botón Consejos
        binding.btnConsejos.visibility = View.VISIBLE
        binding.btnConsejos.setOnClickListener {
            val consejos = resources.getStringArray(R.array.consejos_senderismo).toList()
            val intent = Intent(this, ConsejosSenderismo::class.java)
            intent.putStringArrayListExtra("consejos", ArrayList(consejos))
            startActivity(intent)
        }

        // Funcionalidad del botón Volver
        binding.btnVolver.setOnClickListener {
            finish() // Volver a la pantalla anterior
        }

        // Funcionalidad del botón Agregar Ruta
        binding.btnAgregarRuta.setOnClickListener {
            val intent = Intent(this, ContenedorFragmentAgregarRuta::class.java)
            agregarRutaLauncher.launch(intent)
        }
    }

    /**
     * Inicializa las rutas y las agrega al ViewModel
     */
    private fun inicializarRutas() {
        viewModel.agregarRuta(RutasSenderismo(getString(R.string.ruta_montania), getString(R.string.desc_montana), "8 km", getString(R.string.dif_dificil), 180))
        viewModel.agregarRuta(RutasSenderismo(getString(R.string.ruta_lago), getString(R.string.desc_lago), "6 km", getString(R.string.dif_moderada), 150))
        viewModel.agregarRuta(RutasSenderismo(getString(R.string.ruta_bosque), getString(R.string.desc_bosque), "5 km", getString(R.string.dif_facil), 120))
    }
}
