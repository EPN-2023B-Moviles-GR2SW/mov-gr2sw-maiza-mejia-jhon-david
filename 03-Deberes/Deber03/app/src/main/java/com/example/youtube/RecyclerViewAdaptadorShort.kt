package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.model.Short
import java.util.ArrayList

class RecyclerViewAdaptadorShort(
    private val contexto: RecyclerViewShorts,
    private val lista: ArrayList<Short>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter <RecyclerViewAdaptadorShort.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val shortImageView: ImageView
        init {
            shortImageView = view.findViewById(R.id.ivShort)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_short,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shortActual = this.lista[position]
        holder.shortImageView.setImageResource(shortActual.imagen)
        "Nombre:${shortActual.imagen}"
    }
}