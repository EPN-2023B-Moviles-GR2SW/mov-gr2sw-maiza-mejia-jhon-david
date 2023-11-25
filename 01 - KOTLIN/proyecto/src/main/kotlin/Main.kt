import java.util.*
import kotlin.collections.ArrayList

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

    val sumaUno = Suma(1, 1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma (null, null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSuma)

    // Arreglo estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    // Arreglo dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    // For each -> Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int ->
            println("Valor Actual: ${valorActual}")
        }

    // it significa el elemento iterado
    arregloDinamico.forEach{ println("Valor actual. ${it}") }

    arregloEstatico
        .forEachIndexed{ indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice. ${indice}")
        }
    println(respuestaForEach)

    // Map -> muta el arreglo (cambia el arreglo)
    // 1) Enviemos el nuevo valor de la iteracion
    // 2) Nos devuelve es un Nuevo Arreglo con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map {it + 15}


    // Filter -> filtrar el arreglo
    // 1) Devolver una expresion (true o false)
    // 2) Nuevo arreglo filtrado
    val respuestaFilter: List <Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayorACinco: Boolean = valorActual > 5
            return@filter mayorACinco
        }
    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    // or and
    // or -> any (Alguno cumple?)
    // and -> (todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any {valorActual: Int ->
            return@any (valorActual > 5)
        }

    println(respuestaAny) // true

    val respuestaAll: Boolean = arregloDinamico
        .all{ valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll) // false

    // Reduce -> valor acumulado
    // valor acumulado = 0 (Siemrpe 0 en lenguaje kotlin)
    // [1, 2, 3, 4, 5] -> sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce {// acumulado = 0 -> siempre empiza en cero)
                acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)
        }
    println(respuestaReduce) //78
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
abstract class Numeros(//Constructor PRIMARIO
    // Ejemplo:
    // uno: INT, (Parametro (Sin modificador de acceso))
    // private var uno Int, // Propiedad Publica Clase numeros.uno
    // var uno: Int, // Propiedad de la calse (por defecto Public)
    // public var uno: Int
    protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, //Propiedad de la clase protected numeros.numeroDos

    // var cedula: string = "" (public es por defecto)
    /// private valorCalculado: Int = 0 (private)
){
    init { // bloque del constructor primario
        this.numeroUno; this.numeroDos // this es opcional
        numeroUno; numeroDos; // sin el this es lo mismo
        println("Inicializando")
    }
}

class Suma (// Constructor primario suma
    unoParametro: Int,
    dosParametro: Int,
    ): Numeros(unoParametro, dosParametro){
        init{ // bloque codigo constructor primario
            this.numeroUno
            this.numeroDos
        }

    constructor(// segundo constructor
        uno: Int?,
        dos: Int
    ):this(
        if(uno == null) 0 else uno,
        dos
    )
    constructor(// tercer constructor
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos == null) 0 else dos,
    )

    constructor(// cuarto constructor
        uno: Int?,
        dos: Int?
    ): this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos
    )

    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        agregarHistorial(total)
        return total
    }

    // Atributos y metodos "Compartidos"
    companion object {
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int{
            return num * num
        }
        val historialSuma = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSuma.add(valorNuevaSuma)
        }
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
