package com.example.examen01

import java.time.LocalDate

class Profesor (
    val cedula: String,
    val nombre: String,
    val esProfesorTitular: Boolean,
    var sueldo: Double,
    var materias: MutableList<Materia> = arrayListOf()
) {
    override fun toString(): String {
        return "Cédula : $cedula\nNombre : $nombre\nEs Profesor Titular : $esProfesorTitular\nSueldo : $$sueldo"
    }
}