package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditarMateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_materia)

        val codigo = findViewById<EditText>(R.id.inputEditarCodigo)
        val nombre = findViewById<EditText>(R.id.inputEditarNombreMateria)
        val horas = findViewById<EditText>(R.id.inputEditarHoras)
        val creditos = findViewById<EditText>(R.id.inputEditarCreditos)

        codigo.setText(BaseDatosMemoria.materiaSeleccionada.codigo)
        nombre.setText(BaseDatosMemoria.materiaSeleccionada.nombre)
        horas.setText(BaseDatosMemoria.materiaSeleccionada.horas.toString())
        creditos.setText(BaseDatosMemoria.materiaSeleccionada.creditos.toString())

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

        val materiaEditada = Materia(
            codigo.text.toString(),
            nombre.text.toString(),
            creditos.text.toString().toInt(),
            horas.text.toString().toInt()
        )
        BaseDatosMemoria.profesorSelecciondo.materias.forEachIndexed { index, materia ->
            if (materia.codigo === BaseDatosMemoria.materiaSeleccionada.codigo) {
                BaseDatosMemoria.profesorSelecciondo.materias[index] = materiaEditada
            }
        }
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}