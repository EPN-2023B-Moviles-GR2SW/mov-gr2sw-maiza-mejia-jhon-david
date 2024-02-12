package com.example.examen02.modelo

class Profesor (
    val cedula: String,
    val nombre: String,
    val esProfesorTitular: Boolean,
    var sueldo: Long,
    var materias: MutableList<Materia>? = null
) {
    override fun toString(): String {
        return "Cédula : $cedula\nNombre : $nombre\nEs Profesor Titular : $esProfesorTitular\nSueldo : $$sueldo"
    }
}