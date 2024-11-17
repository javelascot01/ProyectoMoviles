package com.javt.proyectojesusvelasco.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class PagerAdapter(fragmentActivity: FragmentActivity, private val rutas: List<RutasSenderismo>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = rutas.size // El número de elementos es igual al número de rutas

    override fun createFragment(position: Int): Fragment {
        // Devuelve un fragmento para la ruta correspondiente en la posición
        return RutaFragment(rutas[position])
    }
}