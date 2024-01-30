package com.example.examen01.database

import com.example.examen01.modelo.Materia
import com.example.examen01.modelo.Profesor

class BaseDeDatos {
    companion object{
        var tablaProfesor: SqliteHelperProfesor? = null
        var tablaMateria: SqliteHelperMateria? = null
        var profesorSelecciondo = Profesor("00000000", "nombre", false, 0.0, arrayListOf())
        var materiaSeleccionada = Materia("","", 0, 0)
    }
}
