package com.example.examen01.modelo

class Materia (
    val codigo: String,
    val nombre: String,
    var creditos: Int,
    var horas: Int,
) {

    override fun toString(): String {
        return "Código : $codigo\nNombre : $nombre\nCréditos : $creditos\nHoras : $horas"
    }
}