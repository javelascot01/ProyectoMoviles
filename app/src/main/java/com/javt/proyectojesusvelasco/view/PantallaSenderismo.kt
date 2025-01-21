package com.javt.proyectojesusvelasco.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.javt.proyectojesusvelasco.view.adapter.PagerAdapterDificultad
import com.javt.proyectojesusvelasco.viewModel.RutasSenderismoViewModel
import java.lang.Thread.sleep

class PantallaSenderismo : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaSenderismoBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    //private lateinit var adapter: PagerAdapter
    private lateinit var adapter: PagerAdapterDificultad
    private lateinit var rutasPorDificultad : Map<Dificultad, List<RutasSenderismo>>
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
        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager2

        // Inicializo el TabLayout y ViewPager2
        viewModel.rutas.observe(this) { rutas ->
            if (rutas != null) {
                Log.d("RutasActualizadas", "Rutas recibidas: $rutas")
                rutasPorDificultad = rutas.groupBy { it.dificultad }
                adapter = PagerAdapterDificultad(this, rutasPorDificultad)
                viewPager2.adapter = adapter
                TabLayoutMediator(tabLayout, viewPager2) { tab, index ->
                    val dificultad = Dificultad.entries[index]
                    tab.text = dificultad.aString(this)
                }.attach()
            } else {
                Log.d("RutasActualizadas", "No se recibieron rutas")
            }
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
    /*
    private fun inicializarRutas() {
        viewModel.agregarRuta(RutasSenderismo(id=null,
            getString(R.string.ruta_montania),
            getString(R.string.desc_montana),
            "8",
            Dificultad.FACIL,
            180))

        viewModel.agregarRuta(RutasSenderismo(id=null,
            getString(R.string.ruta_lago),
            getString(R.string.desc_lago),
            "6",
            Dificultad.DIFICIL, 150))

        viewModel.agregarRuta(RutasSenderismo(
            id=null,
            getString(R.string.ruta_bosque),
            getString(R.string.desc_bosque),
            "5",
            Dificultad.MEDIA,
            120))
    }*/

    override fun onResume() {
        super.onResume()
        rutasPorDificultad = viewModel.obtenerRutas().groupBy { it.dificultad }
        adapter = PagerAdapterDificultad(this, rutasPorDificultad)
        viewPager2.adapter = adapter
    }
}
