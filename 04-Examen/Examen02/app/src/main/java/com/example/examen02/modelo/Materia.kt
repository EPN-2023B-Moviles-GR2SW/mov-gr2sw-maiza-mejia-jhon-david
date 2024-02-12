package com.example.examen02.modelo

class Materia (
    val codigo: String,
    val nombre: String,
    var creditos: Long,
    var horas: Long,
    var cedulaProfesor: String? = null,
) {

    override fun toString(): String {
        return "Código : $codigo\nNombre : $nombre\nCréditos : $creditos\nHoras : $horas"
    }
}