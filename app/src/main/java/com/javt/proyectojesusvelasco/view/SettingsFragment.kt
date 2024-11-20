package com.javt.proyectojesusvelasco.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.javt.proyectojesusvelasco.R

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener (this)
    }
    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

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