package com.example.proyecto02.modelo

import androidx.annotation.StringDef

class Pelicula(
    var titulo: String,
    var calificacion: String,
    var duracion: String,
    var imagen: Int
) {
    override fun toString(): String {
        return "Pelicula(titulo='$titulo', calificacion='$calificacion', duracion='$duracion', imagen=$imagen)"
    }
}