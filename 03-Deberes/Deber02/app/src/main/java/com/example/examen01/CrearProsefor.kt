package com.example.examen01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.examen01.database.BaseDeDatos

class CrearProsefor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_prosefor)

        val btnCrearNuevoProsefor = findViewById<Button>(R.id.btnCrearNuevoProfesor)
        btnCrearNuevoProsefor.setOnClickListener{
            crearNuevoProfesor()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearProfesor)
        btnCancelar.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }

    fun crearNuevoProfesor(){
        val nombre = findViewById<EditText>(R.id.inputNombre)
        val cedula = findViewById<EditText>(R.id.inputCedula)
        val sueldo = findViewById<EditText>(R.id.inputSueldo)
        val esProfesorTitular = findViewById<EditText>(R.id.inputProfesorTitular)
        val esProfesorTitularText = esProfesorTitular.text.toString()
        val esProfesorTitularValue = esProfesorTitularText.toInt()

        BaseDeDatos.tablaProfesor!!.crearProfesor(cedula.text.toString(),nombre.text.toString(), esProfesorTitularValue, sueldo.text.toString().toDouble() )

    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}