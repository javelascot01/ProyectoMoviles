package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityPantallaDetalleRutaBinding
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class PantallaDetalleRuta : AppCompatActivity() {
    private lateinit var binding: ActivityPantallaDetalleRutaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaDetalleRutaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtengo la ruta del intent
        val ruta = intent.getSerializableExtra("ruta", RutasSenderismo::class.java)
        binding.txtDescripcionRuta.text = ruta?.descripcion
        binding.txtDistanciaRuta.text = buildString {
            append(ruta?.distancia)
            append(getString(R.string.km))
        }
        binding.txtDificultadRuta.text = ruta?.dificultad?.aString(this)
        binding.txtDuracionRuta.text = buildString {
            append(ruta?.duracion.toString())
            append(" ")
            append(getString(R.string.minutos))
        }
    }
}