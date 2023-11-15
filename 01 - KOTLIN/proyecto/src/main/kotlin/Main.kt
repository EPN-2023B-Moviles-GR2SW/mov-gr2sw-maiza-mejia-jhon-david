import java.util.*

fun main(args: Array<String>){
    println("Hello World!")
    // Inmutables (No se reasignan "=")
    val inmutable: String = "Adrian";
    // inmutable = "Vicente";

    // Mutables (Re asignar)
    var mutable: String = "Vicente";
    mutable = "Adrian";

    // val > var
    // Duck Typing
    var ejemploVariable = "Jhon Maiza";
    var edadEjemplo: Int = 12;
    ejemploVariable.trim();
    //ejemploVariable = edadEjemplo;

    // Variable primitiva
    val nombreProfesor: String = "Adrian Equez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    // Clases Java
    val fechaNacimiento: Date = Date();

    // Switch
    val estadoCivelWhen = "C"
    when (estadoCivelWhen) {
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val esSoltero = (estadoCivelWhen == "s")
    val coqueteo = if(esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00);
    calcularSueldo(10.00, bonoEspecial = 20.00) // Named Parameters
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) // Parametros nombrado

}

abstract class Numerosjava{
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno:Int,
        dos: Int
    ){ // bloque de codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializadod")
    }
}




// void -> Unit
fun imprimirNombre (nombre: String): Unit{
    println("Nombre: ${nombre}") // template strings
}

fun calcularSueldo(
    sueldo: Double, // requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null, // Opcion null -> nullable
): Double{
    // Int -> Int? (nullable)
    // String -> String? (nullable)
    // Date -> Date? (nullable)
    if (bonoEspecial == null){
        return sueldo * (100 / tasa)

    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}
