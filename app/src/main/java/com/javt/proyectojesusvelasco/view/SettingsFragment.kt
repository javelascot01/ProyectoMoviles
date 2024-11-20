package com.javt.proyectojesusvelasco.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.javt.proyectojesusvelasco.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    // Método que se llama cuando se crea el fragmento de preferencias
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // Establece las preferencias desde el archivo xml preferences
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    // Método llamado cuando el fragmento entra en primer plano
    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener (this)
    }
    // Método llamado cuando el fragmento pasa a segundo plano
    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    // Método que se ejecuta cuando alguna preferencia cambia según la key realiza alguna opcion
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when(key){
            "pref_checkbox" ->{
                val isNotificationEnabled = sharedPreferences?.getBoolean(key, true) ?: true
            }
            "pref_texto"->{
                val userName = sharedPreferences?.getString(key, getString(R.string.usuario)) ?: getString(R.string.usuario)
            }
        }
    }


}