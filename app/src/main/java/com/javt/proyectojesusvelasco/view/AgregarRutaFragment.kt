package com.javt.proyectojesusvelasco.view

import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.FragmentAgregarRutaBinding
import com.javt.proyectojesusvelasco.model.RutasSenderismo
import com.javt.proyectojesusvelasco.viewModel.RutasSenderismoViewModel


class AgregarRutaFragment : Fragment() {
    private lateinit var binding: FragmentAgregarRutaBinding
    private val rutasViewModel: RutasSenderismoViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAgregarRutaBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnGuardar.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            val distancia = binding.etDistancia.text.toString()

            // Validar dificultad
            val dificultad = when (binding.rgDificultad.checkedRadioButtonId) {
                R.id.rbFacil -> getString(R.string.dif_facil)
                R.id.rbModerada -> getString(R.string.dif_moderada)
                R.id.rbDificil -> getString(R.string.dif_dificil)
                else -> null
            }

            // Validar que todos los campos esten completos
            val duracion = binding.etDuracion.text.toString().toIntOrNull() // Uso toIntOrNull para evitar nulos en caso de que no sea un número

            if (nombre.isBlank() || descripcion.isBlank() || distancia.isBlank() || dificultad == null || duracion == null) {
                // Mensaje de error
                Toast.makeText(context, getString(R.string.error_seleccion), Toast.LENGTH_SHORT).show()
            } else {
                // Crear la nueva ruta y agregarla al viewModel
                val nuevaRuta = RutasSenderismo(nombre, descripcion, distancia, dificultad, duracion)

                val intent = activity?.intent
                intent?.putExtra("nuevaRuta", nuevaRuta)
                activity?.setResult(AppCompatActivity.RESULT_OK, intent)
                // cerrar fragmento
                activity?.finish()
            }
        }

        // Configurar el botón de cancelar
        binding.btnCancelar.setOnClickListener {
            activity?.finish()
        }

        return view
    }
}
