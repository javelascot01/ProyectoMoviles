package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.FragmentRutaBinding
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class RutaFragment(private val ruta: RutasSenderismo) : Fragment(R.layout.fragment_ruta) {

    private lateinit var binding: FragmentRutaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRutaBinding.inflate(inflater, container, false)
        val view = binding.root
        // Configuramos los datos de la ruta en el layout
        binding.txtDescripcionRuta.text = ruta.descripcion
        binding.txtDistanciaRuta.text = ruta.distancia
        binding.txtDificultadRuta.text = ruta.dificultad
        binding.txtDuracionRuta.text = ruta.duracion.toString()+" "+getString(R.string.minutos)

        return view
    }
}