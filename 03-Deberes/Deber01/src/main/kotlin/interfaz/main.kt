package interfaz

import kotlinx.datetime.LocalDate
import modelo.Materia
import modelo.Profesor

fun main() {

    while (true) {
        println("--------------------------------------------------")
        println("Programa para la Gestión de Profesores y Materias.")
        print("--------------------------------------------------")
        println("\nMenú:")
        println("1. Mostrar profesores y sus materias")
        println("2. Menú de Profesores")
        println("3. Menú de Materias")
        println("4. Salir")

        print("\nIngrese su opción: ")
        when (readLine()?.toIntOrNull()) {
            1 -> readProfesores()
            2 -> menuCrudProfesores()
            3 -> menuCrudMaterias()
            4 -> return
            else -> println("\nOpción inválida. Inténtelo de nuevo.")
        }
    }
}

fun menuCrudProfesores() {
    println("\nMenú Profesores:")
    println("1. Crear profesor")
    println("2. Leer profesores")
    println("3. Leer profesor por cédula")
    println("4. Actualizar profesor")
    println("5. Borrar profesor")
    println("6. Volver al menú principal")

    print("Ingrese su opción: ")
    when (readlnOrNull()?.toIntOrNull()) {
        1 -> createProfesor()
        2 -> readProfesores()
        3 -> readProfesoreByCedula()
        4 -> updateProfesor()
        5 -> deleteProfesor()
        6 -> return
        else -> println("\nOpción inválida. Inténtelo de nuevo.")
    }
}

fun menuCrudMaterias() {
    println("\nMenú Materias:")
    println("1. Crear materia")
    println("2. Leer materias")
    println("3. Leer materia por código")
    println("4. Actualizar materia")
    println("5. Borrar materia")
    println("6. Volver al menú principal")

    print("Ingrese su opción: ")
    when (readlnOrNull()?.toIntOrNull()) {
        1 -> createMateria()
        2 -> readMaterias()
        3 -> readMateriasByCodigo()
        4 -> updateMateria()
        5 -> deleteMateria()
        6 -> return
        else -> println("\nOpción inválida. Inténtelo de nuevo.")
    }
}

fun createProfesor(){
    print("\nIngrese la cédula del profesor: ")
    val cedula = readLine() ?: return

    print("Ingrese el nombre del profesor: ")
    val nombre = readLine() ?: return

    print("Ingrese el sexo del profesor (h/m): ")
    var sexo = 'N'
    when (readLine()?.trim()?.lowercase()) {
        "h" -> {
            sexo = 'H'
        }
        "m" -> {
            sexo = 'M'
        }
        else -> {
            println("\nRespuesta inválida. Registre nuevamente al profesor.")
            return
        }
    }

    print("¿Es profesor titular? (v/f): ")
    var esProfesorTitular = false
    when (readLine()?.trim()?.lowercase()) {
        "v" -> {
            esProfesorTitular = true
        }
        "f" -> {
            esProfesorTitular = false
        }
        else -> {
            println("\nRespuesta inválida. Registre nuevamente al profesor.")
            return
        }
    }

    print("Ingrese el sueldo del profesor: ")
    val sueldo = readLine()?.toDoubleOrNull() ?: return println("\nRespuesta inválida. Registre nuevamente al profesor.")

    print("Ingrese la fecha de contratación (YYYY-MM-DD): ")
    val fechaContratacion: LocalDate
    try{
        fechaContratacion = LocalDate.parse(readlnOrNull().toString())
    } catch (e: Exception) {
        println("Respuesta inválida. Registre nuevamente al profesor.")
        return
    }

    val materias: ArrayList<Materia> = arrayListOf()

    var bandera = true
    while (bandera) {
        print("\n¿Desea registrar una materia al profesor? (s/n):\n")
        when (readLine()?.trim()?.lowercase()) {
            "s" -> {
                val materiaNueva = createMateria();
                if (materiaNueva != null) {
                    materias.add(materiaNueva)
                }
            }
            "n" -> {
                bandera = false;
            }
            else -> {
                println("\nRespuesta inválida. Registre nuevamente al profesor.")
                return
            }
        }
    }
    val nuevoProfesor = Profesor(cedula, nombre, sexo, esProfesorTitular, sueldo, fechaContratacion, materias)
    Profesor.create(nuevoProfesor)

    println("\nProfesor agregado exitosamente.")
}

fun readProfesores(){
    Profesor.getProfesores().forEachIndexed { index, profesor ->
        println("Profesor ${index + 1}: $profesor")
    }
}

fun readProfesoreByCedula(){
    print("\nIngrese la cédula del profesor que desea buscar: ")
    val cedula = readLine() ?: return
    if(Profesor.readByCedula(cedula) != null){
        println()
        println(Profesor.readByCedula(cedula))
    }
}

fun updateProfesor(){
    print("\nIngrese la cédula del profesor que desea actualizar: ")
    val cedula = readLine() ?: return
    if(Profesor.readByCedula(cedula) == null){
        return
    }
    print("Ingrese el nuevo sueldo del profesor: ")
    val sueldo = readLine()?.toDoubleOrNull() ?: return
    Profesor.update(cedula, sueldo)
    println("\nSe actualizó correctamente. ")
}

fun deleteProfesor(){
    print("\nIngrese la cédula del profesor que desea borrar: ")
    val cedula = readLine() ?: return
    if (Profesor.readByCedula(cedula) != null){
        println("¿Está seguro de que desea borrar? (S/N)")
        when (readLine()?.trim()?.lowercase()) {
            "s" -> {
                Profesor.deleteByCedula(cedula)
                println("El profesor/ra con cédula $cedula se eliminó")
            }
            "n" -> println("\nOperación de borrado cancelada.")
            else -> println("\nRespuesta inválida. Operación de borrado cancelada.")
        }
    }
}

fun createMateria(): Materia?{
    print("\nIngrese el nombre de la materia: ")
    val nombreMateria = readLine() ?: return null

    print("Ingrese el código de la materia: ")
    val codigoMateria = readLine() ?: return null

    print("Ingrese el número de créditos: ")
    val creditosMateria = readLine()?.toIntOrNull() ?: return null

    print("Ingrese el número de horas: ")
    val horasMateria = readLine()?.toIntOrNull() ?: return null

    print("Ingrese la Unidad académica, Básica(B) ó Profecional(P) ó Integración(I): ")
    var unidadAcademica = 'B'
    when (readLine()?.trim()?.lowercase()) {
        "b" -> {
            unidadAcademica = 'B'
        }
        "p" -> {
            unidadAcademica = 'P'
        }
        "i" -> {
            unidadAcademica = 'I'
        }
        else -> {
            println("\nRespuesta inválida. Registre nuevamente al profesor.")
            return null
        }
    }

    val nuevamateria = Materia(nombreMateria, codigoMateria, creditosMateria, horasMateria, unidadAcademica)
    Materia.create(nuevamateria)
    println("\nMateria registrada exitosamente.")
    return nuevamateria
}

fun readMaterias(){
    Materia.getMaterias().forEachIndexed { index, materia ->
        println("Materia ${index + 1}: $materia")
    }
}

fun readMateriasByCodigo(){
    print("\nIngrese el código de la materia que desea buscar: ")
    val codigo = readLine() ?: return
    if(Materia.readByCodigo(codigo) != null){
        println()
        println(Materia.readByCodigo(codigo))
    }
}

fun updateMateria(){
    print("\nIngrese el código de la materia que desea actualizar: ")
    val codigo = readLine() ?: return
    if(Materia.readByCodigo(codigo) == null){
        return
    }
    print("Ingrese el nuevo número de creditos de la materia: ")
    val creditos = readLine()?.toIntOrNull() ?: return
    print("Ingrese el nuevo número de horas de la materia: ")
    val horas = readLine()?.toIntOrNull() ?: return
    Materia.update(codigo, creditos, horas)
    println("\nSe actualizó correctamente. ")
}

fun deleteMateria(){
    print("\nIngrese el código de la materia que desea borrar: ")
    val codigo = readLine() ?: return
    if (Materia.readByCodigo(codigo) != null) {
        println("¿Está seguro de que desea borrar? (S/N)")
        when (readLine()?.trim()?.lowercase()) {
            "s" -> {
                Materia.deleteByCodigo(codigo)
                println("La materia con código $codigo se eliminó")
            }
            "n" -> println("\nOperación de borrado cancelada.")
            else -> println("\nRespuesta inválida. Operación de borrado cancelada.")
        }
    }
}







