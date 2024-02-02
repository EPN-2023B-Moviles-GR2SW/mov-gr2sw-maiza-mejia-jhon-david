package com.example.youtube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.dataBase.BaseDatosMemoria

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarRecyclerView()

        val imageViewShorts = findViewById<ImageView>(R.id.ivShortWhite)
        imageViewShorts.setOnClickListener {
            irActividad(RecyclerViewShorts::class.java)
        }
    }

    fun inicializarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_videos)
        val adaptador = RecyclerViewAdaptadorNombreDescripcion(
            this,
            BaseDatosMemoria.arregloVideos,
            recyclerView
        )

        val recyclerViewCategorias = findViewById<RecyclerView>(R.id.rv_categorias)
        val adaptadorCategoria = RecyclerViewAdaptadorCategoria(
            this,
            BaseDatosMemoria.arregloCategorias,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

        recyclerViewCategorias.adapter = adaptadorCategoria
        recyclerViewCategorias.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerViewCategorias.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adaptadorCategoria.notifyDataSetChanged()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}