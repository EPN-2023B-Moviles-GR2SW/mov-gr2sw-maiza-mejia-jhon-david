package com.example.proyecto02

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto02.modelo.Pelicula
import java.util.ArrayList

class RecyclerViewAdapterPeliculas (
    private val contexto: Context,
    private val lista: ArrayList<Pelicula>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter <RecyclerViewAdapterPeliculas.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView: ImageView
        val tituloTextView: TextView
        val calificacionTextView: TextView
        val duracionTextView: TextView
        val informacionButton: ImageButton

        init {
            imageView = view.findViewById(R.id.iv_pelicula)
            tituloTextView = view.findViewById(R.id.tv_titulo)
            calificacionTextView = view.findViewById(R.id.tv_calificacion)
            duracionTextView = view.findViewById(R.id.tv_duracion)
            informacionButton = view.findViewById(R.id.ib_informacion)

            informacionButton.setOnClickListener{
                irActividad(DetallePelicula::class.java)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_pelicula,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val peliculaActual = this.lista[position]
        holder.imageView.setImageResource(peliculaActual.imagen)
        holder.tituloTextView.text = peliculaActual.titulo
        holder.calificacionTextView.text = peliculaActual.calificacion
        holder.duracionTextView.text = peliculaActual.duracion
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(contexto, clase)
        contexto.startActivity(intent)
    }

}