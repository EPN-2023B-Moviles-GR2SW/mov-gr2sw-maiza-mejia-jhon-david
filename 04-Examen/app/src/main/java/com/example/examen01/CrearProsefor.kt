package com.example.examen01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CrearProsefor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_prosefor)

        val btnCrearNuevoProsefor = findViewById<Button>(R.id.btnCrearNuevoProfesor)
        btnCrearNuevoProsefor.setOnClickListener{
            crarNuevoProfesor()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearProfesor)
        btnCancelar.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }

    fun crarNuevoProfesor(){
        val nombre = findViewById<EditText>(R.id.inputNombre)
        val cedula = findViewById<EditText>(R.id.inputCedula)
        val sueldo = findViewById<EditText>(R.id.inputSueldo)
        val esProfesorTitular = findViewById<EditText>(R.id.inputProfesorTitular)
        val esProfesorTitularBoolen: Boolean = esProfesorTitular.text.toString().uppercase().equals("VERDADERO")


        val nuevoProfesor = Profesor(cedula.text.toString(),nombre.text.toString(), esProfesorTitularBoolen, sueldo.text.toString().toDouble() )
        BaseDatosMemoria.arregloProfesores.add(nuevoProfesor)
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}