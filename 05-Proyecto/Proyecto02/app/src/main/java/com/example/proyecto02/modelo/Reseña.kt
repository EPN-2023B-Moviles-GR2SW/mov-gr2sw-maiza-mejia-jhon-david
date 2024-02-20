package com.example.proyecto02.modelo

class Reseña (
    val usuario: String,
    val fecha: String,
    val comentrario: String,
    val calificaion: Float
){
    override fun toString(): String {
        return "Reseña(usuario='$usuario', fecha='$fecha', comentrario='$comentrario')"
    }
}