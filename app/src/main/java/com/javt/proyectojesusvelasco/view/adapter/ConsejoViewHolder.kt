package com.javt.proyectojesusvelasco.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.javt.proyectojesusvelasco.databinding.ItemConsejoBinding

class ConsejoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemConsejoBinding.bind(itemView)
    val imgIcono = binding.imgIcono
    val txtConsejo = binding.txtConsejo
}
