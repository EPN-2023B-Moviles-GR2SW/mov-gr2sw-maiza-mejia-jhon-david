package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen01.database.BaseDeDatos


class EditarProfesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_profesor)

        val nombre = findViewById<EditText>(R.id.inputEditarNombre)
        val cedula = findViewById<EditText>(R.id.inputEditarCedula)
        val sueldo = findViewById<EditText>(R.id.inputEditarSueldo)
        val esProfesorTitular = findViewById<EditText>(R.id.inputEditarProfesorTitular)


        nombre.setText(BaseDeDatos.profesorSelecciondo.nombre)
        cedula.setText(BaseDeDatos.profesorSelecciondo.cedula)
        sueldo.setText(BaseDeDatos.profesorSelecciondo.sueldo.toString())
        esProfesorTitular.setText(BaseDeDatos.profesorSelecciondo.esProfesorTitular.toString())

        val btnEditarProfesor = findViewById<Button>(R.id.btnEditarProfesor)
        btnEditarProfesor.setOnClickListener{
            editarProfesor()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarProfesor)
        btnCancelar.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }

    fun editarProfesor(){
        val nombre = findViewById<EditText>(R.id.inputEditarNombre)
        val cedula = findViewById<EditText>(R.id.inputEditarCedula)
        val sueldo = findViewById<EditText>(R.id.inputEditarSueldo)
        val esProfesorTitular = findViewById<EditText>(R.id.inputEditarProfesorTitular)
        BaseDeDatos.tablaProfesor!!.actualizarProfesor(
            cedula.text.toString(),
            nombre.text.toString(),
            esProfesorTitular.text.toString().toInt(),
            sueldo.text.toString().toDouble()
        )
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}