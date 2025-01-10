package com.javt.proyectojesusvelasco.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityPantallaSenderismoBinding
import com.javt.proyectojesusvelasco.model.Dificultad
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


        // Inicializo las rutas y las agregos al ViewModel
        inicializarRutas()

        // Inicializo el TabLayout y ViewPager2
        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager2

        // Configuro el adaptador para el ViewPager2
        adapter = PagerAdapter(this, viewModel.obtenerRutas())
        viewPager2.adapter = adapter

        // Configuro el TabLayout con el ViewPager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, index ->
            tab.text = viewModel.obtenerRutas()[index].nombre
        }.attach()

        viewModel.rutas.observe(this) { rutas ->
            // Actualizo el adaptador cuando las rutas cambian
            adapter = PagerAdapter(this, viewModel.obtenerRutas())
            viewPager2.adapter = adapter
            adapter.updateRutas(rutas)

            // Actualizo los tabs
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
        viewModel.agregarRuta(RutasSenderismo(1,
            getString(R.string.ruta_montania),
            getString(R.string.desc_montana),
            "8",
            Dificultad.FACIL,
            180))

        viewModel.agregarRuta(RutasSenderismo(2,
            getString(R.string.ruta_lago),
            getString(R.string.desc_lago),
            "6",
            Dificultad.DIFICIL, 150))

        viewModel.agregarRuta(RutasSenderismo(3,
            getString(R.string.ruta_bosque),
            getString(R.string.desc_bosque),
            "5",
            Dificultad.MEDIA,
            120))
    }
}
