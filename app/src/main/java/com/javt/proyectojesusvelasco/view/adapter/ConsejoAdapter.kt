package com.javt.proyectojesusvelasco.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.javt.proyectojesusvelasco.R

class ConsejoAdapter(private val consejos: MutableList<String>, private val images: List<Int>) :
    RecyclerView.Adapter<ConsejoViewHolder>() {

    private val selectedItems = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsejoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_consejo, parent, false)
        return ConsejoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsejoViewHolder, position: Int) {
        val consejo = consejos[position]
        holder.binding.txtConsejo.text = consejo
        holder.binding.imgIcono.setImageResource(images[position])

        // Evento click para selección
        holder.itemView.setOnClickListener {
            if (selectedItems.contains(position)) {
                selectedItems.remove(position)
            } else {
                selectedItems.add(position)
            }
            Toast.makeText(holder.itemView.context, "Clicked: $consejo", Toast.LENGTH_SHORT).show()
            notifyItemChanged(position)
        }

        // Evento long click para eliminar
        holder.itemView.setOnLongClickListener {
            val removedPosition = position
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar Consejo")
                .setMessage("¿Estás seguro de que deseas eliminar este consejo?")
                .setPositiveButton("Eliminar") { dialog, _ ->
                    consejos.removeAt(removedPosition)
                    notifyItemRemoved(removedPosition)
                    notifyItemRangeChanged(removedPosition, consejos.size - removedPosition)
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            true
        }

        // Cambiar el color del fondo según si está seleccionado
        if (selectedItems.contains(position)) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.item_seleccionado)
            )
        } else {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.item_no_seleccionado)
            )
        }
    }

    override fun getItemCount(): Int = consejos.size

    fun getSelectedItems(): List<Int> = selectedItems.toList()
}
