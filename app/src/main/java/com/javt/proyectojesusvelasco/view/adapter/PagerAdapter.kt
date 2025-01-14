package com.javt.proyectojesusvelasco.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.javt.proyectojesusvelasco.model.RutasSenderismo
import com.javt.proyectojesusvelasco.view.RutaFragment

class PagerAdapter(fragmentActivity: FragmentActivity, private var rutas: List<RutasSenderismo>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = rutas.size // El número de elementos es igual al número de rutas

    override fun createFragment(position: Int): Fragment {
        // Devuelve un fragmento para la ruta correspondiente en la posición
        return RutaFragment(rutas[position])
    }
    fun updateRutas(nuevasRutas: List<RutasSenderismo>) {
        rutas = nuevasRutas
        notifyDataSetChanged() // Notificar al adaptador que los datos han cambiado
    }
}