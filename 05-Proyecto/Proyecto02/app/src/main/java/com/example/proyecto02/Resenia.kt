package com.example.proyecto02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto02.modelo.Pelicula
import com.example.proyecto02.modelo.Reseña
import com.google.android.material.appbar.MaterialToolbar

class Resenia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resenia)

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

        var inicio = findViewById<LinearLayout>(R.id.ly_inicio)
        inicio.setOnClickListener{
            irActividad(Inicio::class.java)
        }

        val agregarReseñia = findViewById<Button>(R.id.btn_agregar_reseña)
        agregarReseñia.setOnClickListener(){
            irActividad(NuevaResenia::class.java)
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.mtb_reseña)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        var arregloReseñas = arrayListOf<Reseña>()
        arregloReseñas.add(
            Reseña(
            "Juan Perez",
            "01/04/2024",
            "La película de cautiva con su trama intrigante y efectos" +
                    " visuales impresionantes Aunque algunos puntos podrían mejorarse " +
                    "en general es una experiencia emocionante y recomendable.",
                4.0f))
        arregloReseñas.add(
            Reseña(
                "Juan Perez",
                "01/04/2024",
                "La película de cautiva con su trama intrigante y efectos" +
                        " visuales impresionantes Aunque algunos puntos podrían mejorarse " +
                        "en general es una experiencia emocionante y recomendable.",
                4.0f))

        val recyclerView = findViewById<RecyclerView>(R.id.rv_reseñas)
        val adaptador = RecyclerViewAdapterReseñas(
            this,
            arregloReseñas,
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