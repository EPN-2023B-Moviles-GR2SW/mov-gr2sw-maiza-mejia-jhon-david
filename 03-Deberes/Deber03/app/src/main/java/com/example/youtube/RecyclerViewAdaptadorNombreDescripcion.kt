package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.model.Video
import java.util.ArrayList

class RecyclerViewAdaptadorNombreDescripcion(
    private val contexto: MainActivity,
    private val lista: ArrayList<Video>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter <RecyclerViewAdaptadorNombreDescripcion.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView: ImageView
        val tituloTextView: TextView
        val canalTextView: TextView
        val vistasTextView: TextView
        val fechaTextView: TextView

        init {
            imageView = view.findViewById(R.id.imageView)
            tituloTextView = view.findViewById(R.id.tvTitulo)
            canalTextView = view.findViewById(R.id.tvCanal)
            vistasTextView = view.findViewById(R.id.tvVistas)
            fechaTextView = view.findViewById(R.id.tvFecha)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista_videos,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val videoActual = this.lista[position]
        holder.imageView.setImageResource(videoActual.imagen)
        holder.tituloTextView.text = videoActual.titulo
        holder.canalTextView.text = videoActual.canal + " \u00B7 "
        holder.vistasTextView.text = videoActual.vistas + " \u00B7 "
        holder.fechaTextView.text = videoActual.fecha
        "Nombre:${videoActual.titulo}"
    }
}