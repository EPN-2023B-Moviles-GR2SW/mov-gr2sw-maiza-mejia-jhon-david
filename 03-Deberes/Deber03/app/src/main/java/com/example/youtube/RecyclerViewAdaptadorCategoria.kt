package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.model.Categoria
import java.util.ArrayList

class RecyclerViewAdaptadorCategoria(
    private val contexto: MainActivity,
    private val lista: ArrayList<Categoria>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter <RecyclerViewAdaptadorCategoria.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val categoriaButton: Button

        init {
            categoriaButton = view.findViewById(R.id.btnCategoria)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_categorias,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val categoriaActual = this.lista[position]
        holder.categoriaButton.text = categoriaActual.nombre
        "Nombre:${categoriaActual.nombre}"
    }
}