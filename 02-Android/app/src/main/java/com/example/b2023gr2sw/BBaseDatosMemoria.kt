package com.example.b2023gr2sw

class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador.add(BEntrenador(1, "Adrian", "a@as.com"))
            arregloBEntrenador.add(BEntrenador(2, "Vicenete", "vice@as.com"))
            arregloBEntrenador.add(BEntrenador(3, "Carolina", "caro@as.com"))
        }
    }

}