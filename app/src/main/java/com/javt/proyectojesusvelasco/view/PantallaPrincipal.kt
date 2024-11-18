package com.javt.proyectojesusvelasco.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.PantallaPrincipalBinding

class PantallaPrincipal : AppCompatActivity() {
    private lateinit var binding : PantallaPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actividades = resources.getStringArray(R.array.tipos_actividad)
        val activitySpinner: Spinner = binding.spinnerActividades
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,actividades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Mejora el estilo al desplegar el spinner
        activitySpinner.adapter = adapter

        val toolbar: MaterialToolbar =binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Muestra el icono de volver
        binding.exploreButton.setOnClickListener {
            val actividadSeleccionada= binding.spinnerActividades.selectedItem.toString();

            if (actividadSeleccionada.isNotEmpty()) {
                // Usamos un when (switch) para decidir a qué pantalla llevar al usuario
                val actividades= resources.getStringArray(R.array.tipos_actividad)
                when (actividadSeleccionada) {
                    actividades[1] -> { // "Hiking"
                        // Navegar a la pantalla de senderismo
                        val intent = Intent(this, PantallaSenderismo::class.java)
                        startActivity(intent)
                        // Mostrar un toast  para Hiking
                        val toast = Toast.makeText(this,"Has seleccionado Hiking (Senderismo)",Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }

                    actividades[2] -> { // "Cycling"
                        // Navegar a la pantalla de ciclismo
                        val intent = Intent(this, PantallaCiclismo::class.java)
                        startActivity(intent)
                        // Mostrar un toast  para Cycling
                        val toast = Toast.makeText(this,"Has seleccionado Cycling (Ciclismo)",Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }

                    actividades[3] -> { // "Swimming"
                        // Navegar a la pantalla de natación
                        val intent = Intent(this, PantallaNatacion::class.java)
                        startActivity(intent)
                        // Mostrar un toast  para Swimming
                        val toast = Toast.makeText(this,"Has seleccionado Swimming (Natación)",Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }

                    else -> {
                        // Mostrar un mensaje si no se ha seleccionado una actividad válida
                        val toast = Toast.makeText(this,"Por favor, selecciona una actividad válida",Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                }
            } else {
                val toast = Toast.makeText(this, R.string.error_seleccion, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemPaginaWeb -> {
                val webpage: Uri =
                    Uri.parse("https://www.spain.info/es/descubrir-espana/viaje-pueblos-castilla-mancha/")
                val webintent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(webintent)
                true
            }

            R.id.menuItemAcercaDe -> {
                val intent = Intent(this, AcercaDe::class.java)
                startActivity(intent)
                true
            }

            R.id.menuItemPreferencias -> {
                binding.fragmentContainerView.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, SettingsFragment())
                    .addToBackStack(null)
                    .commit()
                binding.exploreButton.setOnClickListener {
                    onBackPressed()
                }
                true
            }

            else -> false
        }

    }
}