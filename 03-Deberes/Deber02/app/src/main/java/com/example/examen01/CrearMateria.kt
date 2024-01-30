package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen01.database.BaseDeDatos
import com.example.examen01.modelo.Materia

class CrearMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_materia)
        val btnCrearNuevaMateria = findViewById<Button>(R.id.btnCrearNUevaMateria)
        btnCrearNuevaMateria.setOnClickListener{
            crarNuevaMateria()
            irActividad(ListViewMaterias::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearMateria)
        btnCancelar.setOnClickListener{
            irActividad(ListViewMaterias::class.java)
        }
    }

    fun crarNuevaMateria(){
        val codigo = findViewById<EditText>(R.id.inputCodigo)
        val nombre = findViewById<EditText>(R.id.inputNombreMateria)
        val horas = findViewById<EditText>(R.id.inputHoras)
        val creditos = findViewById<EditText>(R.id.inputCreditos)

        BaseDeDatos.tablaMateria!!.crearMateria(
            codigo.text.toString(),
            nombre.text.toString(),
            creditos.text.toString().toInt(),
            horas.text.toString().toInt(),
            BaseDeDatos.profesorSelecciondo.cedula
        )

    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}