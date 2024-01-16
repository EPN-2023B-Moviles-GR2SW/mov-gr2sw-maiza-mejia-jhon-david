package com.example.b2023gr2sw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class FRecyclerViewAdapatadorNombreDescripcion (
    private  val contexto: FRecyclearView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter<
        FRecyclerViewAdapatadorNombreDescripcion.MyViewHolder
        >() {
            inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
                val nombreTextView: TextView
                val descripcionTextView: TextView
                val likesTextView: TextView
                val accionButton: Button
                var numeroLikes = 0

                init {
                    nombreTextView = view.findViewById(R.id.tv_nombre)
                    descripcionTextView  = view.findViewById(R.id.tv_descripcion)
                    likesTextView = view.findViewById(R.id.tv_likes)
                    accionButton = view.findViewById(R.id.tv_nombre)
                    accionButton.setOnClickListener { anadirLike() }
                }

                fun anadirLike(){
                    numeroLikes = numeroLikes + numeroLikes
                    likesTextView.text = numeroLikes.toString()
                    contexto.aumentarTotalLikes()
                }
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.descripcionTextView.text = entrenadorActual.descripcion
        holder.likesTextView.text = "0"
        holder.accionButton.text = "ID. ${entrenadorActual.id}"
        "Nombre:${entrenadorActual.nombre}"
    }
}