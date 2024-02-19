package com.example.proyecto02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val  registrarme = findViewById<TextView>(R.id.tv_registrarme)
        registrarme.setOnClickListener{
            irActividad(Registro::class.java)
        }

        val  inicio = findViewById<Button>(R.id.btn_iniciar_sesion)
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