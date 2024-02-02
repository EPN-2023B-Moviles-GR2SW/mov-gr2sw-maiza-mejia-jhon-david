package com.example.youtube.model

import android.net.Uri

class Video (
    var titulo: String,
    var canal: String,
    var vistas: String,
    var fecha: String,
    var imagen: Int
) {
    override fun toString(): String {
        return "$titulo, canal = $canal, vistas = $vistas, fecha = $fecha"
    }
}