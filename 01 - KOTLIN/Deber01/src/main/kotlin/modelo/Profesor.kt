package modelo

import kotlinx.datetime.LocalDate
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

@Serializable
data class Profesor(
    val cedula: String,
    val nombre: String,
    val sexo: Char,
    val esProfesorTitular: Boolean,
    var sueldo: Double,
    val fechaContratacion: LocalDate,
    val materias: ArrayList<Materia>? = arrayListOf()
) {

    companion object {
        fun getProfesores(): ArrayList<Profesor> {
            val archivoProfesores = File("profesores.json")
            if (!archivoProfesores.exists() || archivoProfesores.length() == 0L) {
                archivoProfesores.writeText("[]")
            }
            val profesoresJson = File("profesores.json").readText()

            return Json.decodeFromString<ArrayList<Profesor>>(profesoresJson)
        }

        fun create(profesor: Profesor) {
            val profesoresActualizados = getProfesores() + profesor
            escribirArchivo(profesoresActualizados)
        }

        fun update(cedula: String, sueldo: Double) {
            val profesores = getProfesores()
            profesores.forEach { profesor ->
                if (profesor.cedula == cedula) {
                    profesor.sueldo = sueldo
                }
            }
            escribirArchivo(profesores)
        }

        fun readByCedula(cedula: String): Profesor? {
            val profesorEncontrado = getProfesores().find { it.cedula == cedula }
            return if (profesorEncontrado != null) {
                profesorEncontrado
            } else {
                println("No se encontró al profesor/ra con cédula: $cedula")
                null
            }
        }

        fun deleteByCedula(cedula: String) {
            val profesorEncontrado = readByCedula(cedula)
            if (profesorEncontrado != null) {
                println("¿Está seguro de que desea borrar? (S/N)")
                when (readLine()?.trim()?.lowercase()) {
                    "s" -> {
                        val profesores = getProfesores()
                        profesores.remove(profesorEncontrado)
                        escribirArchivo(profesores)
                        println("El profesor/ra con cédula $cedula se eliminó")
                    }
                    "n" -> println("\nOperación de borrado cancelada.")
                    else -> println("\nRespuesta inválida. Operación de borrado cancelada.")
                }
            }
        }

        private fun escribirArchivo(profesores: List<Profesor>) {
            try {
                File("profesores.json").writeText(Json.encodeToString(profesores))
            } catch (e: IOException) {
                println("Error al escribir en el archivo 'materias.json': ${e.message}")
            }
        }

    }
}
