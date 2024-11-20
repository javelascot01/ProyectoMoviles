package com.javt.proyectojesusvelasco.view

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.appbar.MaterialToolbar
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.PantallaPrincipalBinding

class PantallaPrincipal : AppCompatActivity() {
    private lateinit var binding : PantallaPrincipalBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var isInPreferences = false // Variable para controlar si estoy en preferencias
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Configurar el spinner con las actividades disponibles
        val actividades = resources.getStringArray(R.array.tipos_actividad)
        val activitySpinner: Spinner = binding.spinnerActividades

        // Configurar el adaptador del spinner
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,actividades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Mejora el estilo al desplegar el spinner
        activitySpinner.adapter = adapter

        // Inicializo SharedPreferences para leer los valores guardados
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        // Leer el valor de las preferencias
        val isNotificationEnabled = sharedPreferences.getBoolean("pref_checkbox", true)
        val userName = sharedPreferences.getString("pref_texto", getString(R.string.usuario))

        // Configurar el toolbar
        val toolbar: MaterialToolbar =binding.toolbar
        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Muestra el icono de volver (no funciona)
        binding.toolbar.title = "$userName"                // Muestro el nombre de usuario

        // Configurar el botón de explore
        binding.exploreButton.setOnClickListener {
            if (isInPreferences) {
                // Si estoy en el fragmento de preferencias, volver a la pantalla principal
                salirDePreferencias()
            } else {
                // Si no estoy en preferencias, manejar la navegación según el spinner
                btnExploreManejo()
            }
        }
    }
    // Inflar el menú en el toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Opciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // Navegador
            R.id.menuItemPaginaWeb -> {
                val webpage: Uri =
                    Uri.parse(getString(R.string.webpage_github))
                val webintent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(webintent)
                true
            }

            // Acerca de
            R.id.menuItemAcercaDe -> {
                val intent = Intent(this, AcercaDe::class.java)
                startActivity(intent)
                true
            }

            // Preferencias
            R.id.menuItemPreferencias -> {
                binding.fragmentContainerView.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, SettingsFragment())
                    .addToBackStack(null)
                    .commit()
                binding.exploreButton.text = getString(R.string.back)
                isInPreferences = true
                true
            }

            else -> false
        }

    }
    // Este método se llama cuando la actividad está en primer plano, y actualiza el título con el nombre del usuario
    override fun onResume() {
        super.onResume()
        actualizarTituloUsuario()
    }
    // Actualizo el título de el toolbar con el nombre del usuario guardado en preferencias
    private fun actualizarTituloUsuario() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val userName = sharedPreferences.getString("pref_texto", getString(R.string.usuario))
        binding.toolbar.title = "$userName"

    }
    // Función para salir de preferencias
    private fun salirDePreferencias() {
        // Ocultar el fragmento de preferencias
        supportFragmentManager.popBackStack() // Quitar el fragmento de la pila
        binding.fragmentContainerView.visibility = View.GONE
        binding.exploreButton.text = getString(R.string.explore) // Cambiar el texto del botón
        isInPreferences = false // Actualizar el estado
        actualizarTituloUsuario()
        val toast=Toast.makeText(this, getString(R.string.preferences_saved),Toast.LENGTH_SHORT)
        toast.show()
    }
    private fun btnExploreManejo() {
        val actividadSeleccionada = binding.spinnerActividades.selectedItem.toString()
        Log.d("Jesus", "Actividad seleccionada: $actividadSeleccionada")

        // Comprobador para que actividad se inicia
        if (actividadSeleccionada.isNotEmpty()) {
            val actividades = resources.getStringArray(R.array.tipos_actividad)
            when (actividadSeleccionada) {
                actividades[1] -> {
                    startActivity(Intent(this, PantallaSenderismo::class.java))
                    Toast.makeText(this, getString(R.string.you_selected_hiking), Toast.LENGTH_SHORT).show()
                }
                actividades[2] -> {
                    startActivity(Intent(this, PantallaCiclismo::class.java))
                    Toast.makeText(this, getString(R.string.you_selected_cycling), Toast.LENGTH_SHORT).show()
                }
                actividades[3] -> {
                    startActivity(Intent(this, PantallaNatacion::class.java))
                    Toast.makeText(this, getString(R.string.you_selected_swimming), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, R.string.error_seleccion, Toast.LENGTH_SHORT).show()
                    Log.w("Jesus", "Actividad invalida")
                }
            }
        } else {
            Toast.makeText(this, R.string.error_seleccion, Toast.LENGTH_SHORT).show()
            Log.w("Jesus", "Actividad no seleccionada")
        }
    }
}