package com.javt.proyectojesusvelasco.view

import android.R
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.javt.proyectojesusvelasco.databinding.FragmentRutasBinding
import com.javt.proyectojesusvelasco.model.Dificultad
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class RutasFragment : Fragment() {
    // Atributos
    private lateinit var rutas: List<RutasSenderismo>
    private lateinit var binding: FragmentRutasBinding
    private lateinit var spinner: Spinner
    private lateinit var btnVerDetalle: Button
    private lateinit var selectedRuta: RutasSenderismo

    // Método de clase para crear una instancia del fragmento con una lista de rutas
    companion object {
        fun newInstance(rutas: List<RutasSenderismo>): RutasFragment {
            val fragment = RutasFragment()
            fragment.rutas = rutas
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRutasBinding.inflate(inflater, container, false)
        // Inicializo el Spinner y el botón
        spinner = binding.spinnerRutas
        btnVerDetalle = binding.btnVerDetalle
        // Configuro el Spinner
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            rutas.map { it.nombre }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Manejo la selección de una ruta
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedRuta = rutas[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada si no hay selección
            }
        }

        // Manejar el clic en el botón para ver el detalle
        btnVerDetalle.setOnClickListener {
            val intent = Intent(requireContext(), PantallaDetalleRuta::class.java)
            intent.putExtra("ruta", selectedRuta) // Pasamos la ruta seleccionada al detalle
            startActivity(intent)
        }

        return binding.root
    }

}
