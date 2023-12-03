package modelo

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

@Serializable
data class Materia(
    val nombre: String,
    val codigo: String,
    var creditos: Int,
    var horas: Int,
    val unidadAcademica: Char
) {
    companion object {
        fun getMaterias(): ArrayList<Materia> {
            val archivoMaterias = File("materias.json")
            if (!archivoMaterias.exists() || archivoMaterias.length() == 0L) {
                archivoMaterias.writeText("[]")
            }
            val materiasJson = File("materias.json").readText()
            return Json.decodeFromString<ArrayList<Materia>>(materiasJson)
        }

        fun create(materia: Materia) {
            val materiasActualizadas = getMaterias() + materia
            escribirArchivo(materiasActualizadas)
        }

        fun update(codigo: String, creditos: Int,  horas: Int) {
            val materias = getMaterias()
            materias.forEach { materia ->
                if (materia.codigo == codigo) {
                    materia.horas = horas
                    materia.creditos = creditos
                }
            }
            escribirArchivo(materias)
        }

        fun readByCodigo(codigo: String): Materia? {
            val materiaEncontrada = getMaterias().find { it.codigo == codigo }
            return if (materiaEncontrada != null) {
                materiaEncontrada
            } else {
                println("No se encontró la materia con código: $codigo")
                null
            }
        }

        fun deleteByCodigo(codigo: String) {
            val materiaEncontrada = readByCodigo(codigo)
            if (materiaEncontrada != null) {
                println("¿Está seguro de que desea borrar la materia? (S/N)")
                when (readLine()?.trim()?.lowercase()) {
                    "s" -> {
                        val materias = getMaterias()
                        materias.remove(materiaEncontrada)
                        escribirArchivo(materias)
                        println("La materia con código $codigo se eliminó")
                    }
                    "n" -> println("\nOperación de borrado cancelada.")
                    else -> println("\nRespuesta inválida. Operación de borrado cancelada.")
                }
            }
        }

        private fun escribirArchivo(materias: List<Materia>) {
            try {
                File("materias.json").writeText(Json.encodeToString(materias))
            } catch (e: IOException) {
                println("Error al escribir en el archivo 'materias.json': ${e.message}")
            }
        }

    }
}