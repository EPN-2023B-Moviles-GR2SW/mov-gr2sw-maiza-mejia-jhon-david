package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen02.database.FirestoreMateria
import com.example.examen02.modelo.Materia

class EditarMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_materia)

        val codigo = findViewById<EditText>(R.id.inputEditarCodigo)
        val nombre = findViewById<EditText>(R.id.inputEditarNombreMateria)
        val horas = findViewById<EditText>(R.id.inputEditarHoras)
        val creditos = findViewById<EditText>(R.id.inputEditarCreditos)

        val codigoMateria = intent.getStringExtra("id")
        FirestoreMateria.consultarMateria(codigoMateria!!){
            codigo.setText(it.codigo)
            nombre.setText(it.nombre)
            horas.setText(it.horas.toString())
            creditos.setText(it.creditos.toString())

        }

        val btnEditarMateria = findViewById<Button>(R.id.btnEditarMateria)
        btnEditarMateria.setOnClickListener{
            editarMateria()
            irActividad(ListViewMaterias::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarMateria)
        btnCancelar.setOnClickListener{
            irActividad(ListViewMaterias::class.java)
        }
    }

    fun editarMateria(){
        val codigo = findViewById<EditText>(R.id.inputEditarCodigo)
        val nombre = findViewById<EditText>(R.id.inputEditarNombreMateria)
        val horas = findViewById<EditText>(R.id.inputEditarHoras)
        val creditos = findViewById<EditText>(R.id.inputEditarCreditos)

        val materiaActualizada = Materia (
            codigo.text.toString(),
            nombre.text.toString(),
            creditos.text.toString().toLong(),
            horas.text.toString().toLong()
        )
        FirestoreMateria.actualizarMateria(materiaActualizada)
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val codigoMateria = intent.getStringExtra("id")
        FirestoreMateria.consultarMateria(codigoMateria!!){
            val intent = Intent(this, clase)
            intent.putExtra("cedula", it.cedulaProfesor)
            startActivity(intent)
        }
    }
}