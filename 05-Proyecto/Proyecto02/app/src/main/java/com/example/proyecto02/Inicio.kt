package com.example.proyecto02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto02.modelo.Pelicula

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        var perfil = findViewById<LinearLayout>(R.id.ly_perfil)
        perfil.setOnClickListener{
            irActividad(Perfil::class.java)
        }

        var busqueda = findViewById<LinearLayout>(R.id.ly_busqueda)
        busqueda.setOnClickListener{
            irActividad(Busqueda::class.java)
        }

        var biblioteca = findViewById<LinearLayout>(R.id.ly_biblioteca)
        biblioteca.setOnClickListener{
            irActividad(Biblioteca::class.java)
        }

        var arregloPeliculas = arrayListOf<Pelicula>()
        arregloPeliculas.add(Pelicula("Oppenheimer", "4/5", "181 min.", R.drawable.imagepelicula))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_peliculas_catalogo)
        val adaptador = RecyclerViewAdapterPeliculas(
            this,
            arregloPeliculas,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()

    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}