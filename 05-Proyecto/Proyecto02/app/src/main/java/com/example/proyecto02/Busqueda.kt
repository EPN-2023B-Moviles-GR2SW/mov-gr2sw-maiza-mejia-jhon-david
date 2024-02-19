package com.example.proyecto02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

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

        var inicio = findViewById<LinearLayout>(R.id.ly_busqueda)
        inicio.setOnClickListener{
            irActividad(Inicio::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}