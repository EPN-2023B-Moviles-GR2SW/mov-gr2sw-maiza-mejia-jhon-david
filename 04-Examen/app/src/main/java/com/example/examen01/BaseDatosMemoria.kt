package com.example.examen01

class BaseDatosMemoria {

    companion object {
        val arregloProfesores = arrayListOf<Profesor>()
        val arregloMateriasModelo = arrayListOf<Materia>()
        var profesorSelecciondo = Profesor("00000000", "nombre", false, 0.0, arregloMateriasModelo)
        var materiaSeleccionada = Materia("","", 0, 0)

        init {
            val arregloMateriasGrupoA = arrayListOf<Materia>()
            val arregloMateriasGrupoB = arrayListOf<Materia>()
            arregloMateriasGrupoA.add(Materia("M001", "Geometria", 3, 3))
            arregloMateriasGrupoB.add(Materia("M002", "Lenguaje", 2, 2))
            arregloMateriasGrupoB.add(Materia("M003", "Semiotico", 2, 2))
            arregloProfesores.add(Profesor("1753665968", "Juan", false, 1000.00, arregloMateriasGrupoA))
            arregloProfesores.add(Profesor("1784848648", "Carla", false, 1000.00, arregloMateriasGrupoA))
            arregloProfesores.add(Profesor("1784898758", "Marcos", true, 1500.00, arregloMateriasGrupoB))
        }
    }
}