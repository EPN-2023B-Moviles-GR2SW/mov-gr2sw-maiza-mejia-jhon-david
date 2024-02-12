package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen02.database.Firestore
import com.example.examen02.database.FirestoreMateria
import com.example.examen02.modelo.Materia

class CrearMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_materia)
        var cedulaProfesor = intent.getStringExtra("id")
        val btnCrearNuevaMateria = findViewById<Button>(R.id.btnCrearNUevaMateria)
        btnCrearNuevaMateria.setOnClickListener{
            crarNuevaMateria()
            irActividad(ListViewMaterias::class.java, cedulaProfesor!!)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearMateria)
        btnCancelar.setOnClickListener{
            irActividad(ListViewMaterias::class.java, cedulaProfesor!!)
        }
    }

    fun crarNuevaMateria(){
        val codigo = findViewById<EditText>(R.id.inputCodigo)
        val nombre = findViewById<EditText>(R.id.inputNombreMateria)
        val horas = findViewById<EditText>(R.id.inputHoras)
        val creditos = findViewById<EditText>(R.id.inputCreditos)

        val nuevaMateria = Materia(
            codigo.text.toString(),
            nombre.text.toString(),
            creditos.text.toString().toLong(),
            horas.text.toString().toLong()
        )

        var cedulaProfesor = intent.getStringExtra("id")
        FirestoreMateria.crearMateria(nuevaMateria, cedulaProfesor!!)

        /*var cedulaProfesorSeleccionado = intent.getStringExtra("id")
        println(cedulaProfesorSeleccionado)
        Firestore.consultarProfesor(cedulaProfesorSeleccionado!!) {
            it.materias!!.add(nuevaMateria)
        }*/

    }

    fun irActividad (
        clase: Class <*>, cedulaProfesor: String
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("cedula", cedulaProfesor)
        startActivity(intent)
    }
}