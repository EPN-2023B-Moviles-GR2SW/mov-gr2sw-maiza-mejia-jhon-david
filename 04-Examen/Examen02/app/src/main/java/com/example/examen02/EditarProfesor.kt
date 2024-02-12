package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen02.database.Firestore
import com.example.examen02.modelo.Profesor


class EditarProfesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_profesor)

        val nombre = findViewById<EditText>(R.id.inputEditarNombre)
        val cedula = findViewById<EditText>(R.id.inputEditarCedula)
        val sueldo = findViewById<EditText>(R.id.inputEditarSueldo)
        val esProfesorTitular = findViewById<EditText>(R.id.inputEditarProfesorTitular)


        var cedulaProfesorSeleccionado = intent.getStringExtra("cedula")
        println(cedulaProfesorSeleccionado)
        Firestore.consultarProfesor(cedulaProfesorSeleccionado!!) {
            nombre.setText(it.nombre)
            cedula.setText(it.cedula)
            sueldo.setText(it.sueldo.toString())
            esProfesorTitular.setText(it.esProfesorTitular.toString())
        }


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

        val profesorActualizado = Profesor(
            cedula.text.toString(),
            nombre.text.toString(),
            esProfesorTitular.text.toString().toBoolean(),
            sueldo.text.toString().toLong()
        )
        Firestore.actualizarProfesor(profesorActualizado)
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}