package com.example.proyecto02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto02.modelo.Genero
import com.example.proyecto02.modelo.Pelicula

class Busqueda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda)

        var perfil = findViewById<LinearLayout>(R.id.ly_perfil)
        perfil.setOnClickListener{
            irActividad(Perfil::class.java)
        }

        var biblioteca = findViewById<LinearLayout>(R.id.ly_biblioteca)
        biblioteca.setOnClickListener{
            irActividad(Biblioteca::class.java)
        }

        var inicio = findViewById<LinearLayout>(R.id.ly_inicio)
        inicio.setOnClickListener{
            irActividad(Inicio::class.java)
        }

        var arregloGeneros = arrayListOf<Genero>()
        arregloGeneros.add(Genero("Acción"))
        arregloGeneros.add(Genero("Terror"))
        arregloGeneros.add(Genero("Aventuras"))
        arregloGeneros.add(Genero("Comedia"))
        arregloGeneros.add(Genero("Drama"))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_generos)
        val adaptador = RecyclerViewAdapterGeneros(
            this,
            arregloGeneros,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adaptador.notifyDataSetChanged()

        var arregloPeliculas = arrayListOf<Pelicula>()
        arregloPeliculas.add(Pelicula("Oppenheimer", "4/5", "181 min.", R.drawable.imagepelicula))
        arregloPeliculas.add(Pelicula("Oppenheimer", "4/5", "181 min.", R.drawable.imagepelicula))

        val recyclerViewPelicula = findViewById<RecyclerView>(R.id.rv_peliculas)
        val adaptadorPeliculas = RecyclerViewAdapterPeliculas(
            this,
            arregloPeliculas,
            recyclerViewPelicula
        )
        recyclerViewPelicula.adapter = adaptadorPeliculas
        recyclerViewPelicula.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerViewPelicula.layoutManager = androidx.recyclerview.widget
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