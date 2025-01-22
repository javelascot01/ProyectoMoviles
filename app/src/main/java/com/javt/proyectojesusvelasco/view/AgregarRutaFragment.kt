package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.AlertDialogAgregarRutaBinding
import com.javt.proyectojesusvelasco.databinding.FragmentAgregarRutaBinding
import com.javt.proyectojesusvelasco.model.Dificultad
import com.javt.proyectojesusvelasco.model.RutasSenderismo


class AgregarRutaFragment : Fragment() {

    private lateinit var binding: FragmentAgregarRutaBinding
    private var isAgregarRuta=false;
    private lateinit var dificultades: List<String>
    private lateinit var spinner: Spinner
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgregarRutaBinding.inflate(inflater, container, false)
        val view = binding.root
        spinner = binding.spinner2!!
        dificultades = Dificultad.entries.map { it.aString(this.requireContext()) }
        val adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, dificultades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Configuro el botón para guardar la nueva ruta
        binding.btnGuardar.setOnClickListener {
            showCustomInputAlertDialog()/*
            if(isAgregarRuta){
                val nombre = binding.etNombre.text.toString()
                val descripcion = binding.etDescripcion.text.toString()
                val distancia = binding.etDistancia.text.toString()
                val dificultad = Dificultad.entries[dificultades.indexOf(spinner?.selectedItem.toString())]
                // Validar que todos los campos esten completos
                val duracion = binding.etDuracion.text.toString().toIntOrNull() // Uso toIntOrNull para evitar nulos en caso de que no sea un número
                if (nombre.isBlank() || descripcion.isBlank() || distancia.isBlank() || dificultad == null || duracion == null) {
                    // Mensaje de error
                    Toast.makeText(context, getString(R.string.error_seleccion), Toast.LENGTH_SHORT).show()
                    isAgregarRuta=false // Reseteo boolean para que no se añada la ruta al pulsar otra vez guardar sin haber seleccionado nada en el alert dialog
                } else {
                    // Crear la nueva ruta y agregarla al viewModel
                    val nuevaRuta = RutasSenderismo(1,nombre, descripcion, distancia, dificultad, duracion)
                    // Envio la ruta a la activity para que la cree mediante el launcher
                    val intent = activity?.intent
                    intent?.putExtra("nuevaRuta", nuevaRuta)
                    activity?.setResult(AppCompatActivity.RESULT_OK, intent)
                    // Cerrar fragmento
                    activity?.finish()
                }
            }*/
        }

        // Configuro el botón de cancelar
        binding.btnCancelar.setOnClickListener {
            activity?.finish()
        }

        return view
    }

    /**
     * Muestra un diálogo de alerta personalizado para introducir los datos de la nueva ruta.
     */
    private fun showCustomInputAlertDialog() {
        val builder = AlertDialog.Builder(this.requireContext(), R.style.MyAlertDialogTheme)
        val bindingDialog = AlertDialogAgregarRutaBinding.inflate(layoutInflater)
        builder.setView(bindingDialog.root)
            .setPositiveButton("OK") { dialogInterface, i ->
                // Validar los campos al confirmar
                val nombre = binding.etNombre.text.toString()
                val descripcion = binding.etDescripcion.text.toString()
                val distancia = binding.etDistancia.text.toString()
                val dificultad = Dificultad.entries[dificultades.indexOf(spinner?.selectedItem.toString())]

                // Validar que todos los campos estén completos
                val duracion = binding.etDuracion.text.toString().toIntOrNull()
                if (nombre.isBlank() || descripcion.isBlank() || distancia.isBlank() || dificultad == null || duracion == null) {
                    // Mostrar mensaje de error si algún campo está vacío
                    Toast.makeText(context, getString(R.string.error_seleccion), Toast.LENGTH_SHORT).show()
                } else {
                    // Crear la nueva ruta y agregarla al ViewModel
                    val nuevaRuta = RutasSenderismo(null, nombre, descripcion, distancia, dificultad, duracion)
                    // Enviar la ruta a la actividad para que la guarde
                    val intent = activity?.intent
                    intent?.putExtra("nuevaRuta", nuevaRuta)
                    activity?.setResult(AppCompatActivity.RESULT_OK, intent)
                    // Cerrar el fragmento después de guardar
                    activity?.finish()
                }
            }
            .setNegativeButton("Cancel", null) // No hace nada al cancelar
            .show()
    }
}
