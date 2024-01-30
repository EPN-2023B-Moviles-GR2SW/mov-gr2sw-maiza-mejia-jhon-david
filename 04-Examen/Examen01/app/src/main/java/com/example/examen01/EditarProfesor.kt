package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.examen01.modelo.Profesor

class EditarProfesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_profesor)

        val nombre = findViewById<EditText>(R.id.inputEditarNombre)
        val cedula = findViewById<EditText>(R.id.inputEditarCedula)
        val sueldo = findViewById<EditText>(R.id.inputEditarSueldo)
        val esProfesorTitular = findViewById<EditText>(R.id.inputEditarProfesorTitular)

        nombre.setText(BaseDatosMemoria.profesorSelecciondo.nombre)
        cedula.setText(BaseDatosMemoria.profesorSelecciondo.cedula)
        sueldo.setText(BaseDatosMemoria.profesorSelecciondo.sueldo.toString())
        esProfesorTitular.setText(BaseDatosMemoria.profesorSelecciondo.esProfesorTitular.toString())

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
        val esProfesorTitularBoolen: Boolean = esProfesorTitular.text.toString().uppercase().equals("TRUE")

        val profesorEditado = Profesor(
            cedula.text.toString(),
            nombre.text.toString(),
            esProfesorTitularBoolen,
            sueldo.text.toString().toDouble(),
            BaseDatosMemoria.profesorSelecciondo.materias
        )
        BaseDatosMemoria.arregloProfesores.forEachIndexed{ index, profesor ->
            if(profesor.cedula === BaseDatosMemoria.profesorSelecciondo.cedula ){
                BaseDatosMemoria.arregloProfesores[index] = profesorEditado
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