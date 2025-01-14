package com.javt.proyectojesusvelasco.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.javt.proyectojesusvelasco.model.Dificultad
import com.javt.proyectojesusvelasco.model.RutasSenderismo
import com.javt.proyectojesusvelasco.view.RutaFragment


class PagerAdapterDificultad(
    fragmentActivity: FragmentActivity,
    private val rutasPorDificultad: Map<Dificultad, List<RutasSenderismo>>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return Dificultad.entries.size  // Número de solapas, una por cada dificultad
    }

    override fun createFragment(position: Int): Fragment {
        val dificultad = Dificultad.entries[position]
        val rutas = rutasPorDificultad[dificultad] ?: emptyList()

        // Retorna un fragment que mostrará las rutas de esa dificultad
        return RutasFragment.newInstance(rutas)
    }
}