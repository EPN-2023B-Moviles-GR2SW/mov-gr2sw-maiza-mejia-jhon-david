package com.example.examen01.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen01.modelo.Profesor

class SqliteHelperProfesor (
    contexto: Context,
) : SQLiteOpenHelper(
    contexto,
    "profesores", // nombre BDD
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaProfesor =
            """
               CREATE TABLE PROFESOR(
                   CEDULA VARCHAR(10) PRIMARY KEY,
                   NOMBRE VARCHAR(50),
                   ESPROFESORTITULAR INTEGER,
                   SUELDO REAL
               )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaProfesor)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int,
                           newVersion: Int) {}

    fun crearProfesor(
        cedula: String,
        nombre: String,
        esProfesorTitular: Int,
        sueldo: Double,
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("CEDULA", cedula)
        valoresAGuardar.put("NOMBRE", nombre)
        valoresAGuardar.put("ESPROFESORTITULAR", esProfesorTitular)
        valoresAGuardar.put("SUELDO", sueldo)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "PROFESOR", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarProfesor(cedula: String):Boolean{
        val conexionEscritura = writableDatabase
        // where cedula = ?
        val parametrosConsultaDelete = arrayOf( cedula )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "PROFESOR", // Nombre tabla
                "CEDULA=?", // Consulta Where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarProfesor(
        cedula: String,
        nombre: String,
        esProfesorTitular: Int,
        sueldo: Double,
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("CEDULA", cedula)
        valoresAActualizar.put("NOMBRE", nombre)
        valoresAActualizar.put("ESPROFESORTITULAR", esProfesorTitular)
        valoresAActualizar.put("SUELDO", sueldo)
        // where cedula = ?
        val parametrosConsultaActualizar = arrayOf( cedula )
        val resultadoActualizacion = conexionEscritura
            .update(
                "PROFESOR", // Nombre tabla
                valoresAActualizar, // Valores
                "CEDULA=?", // Consulta Where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt() == -1) false else true
    }

    fun consultarProfesorPorCedula(cedula: String): Profesor{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM Profesor WHERE Cedula = ?".trimIndent()
        val parametrosConsultaLectura = arrayOf(cedula)
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, // Consulta
            parametrosConsultaLectura, // Parametros
        )

        // logica busqueda
        val existeProfesor = resultadoConsultaLectura.moveToFirst()
        var profesorEncontrado = Profesor("0000000000", "" , false, 1000.0)
        if(existeProfesor){
            do{
                val cedula= resultadoConsultaLectura.getString(0)
                val nombre = resultadoConsultaLectura.getString(1)
                val esProfesorTitular = resultadoConsultaLectura.getInt(2)
                val sueldo = resultadoConsultaLectura.getDouble(3)
                if(cedula != null){
                    // llenar el arreglo con un nuevo BEntrenador
                    val profesor = Profesor(
                    cedula,
                    nombre,
                    esProfesorTitular.toString().toBoolean(),
                    sueldo)
                    profesorEncontrado = profesor
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return profesorEncontrado
    }

    fun getAll(): MutableList<Profesor>{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM Profesor".trimIndent()
        val result = baseDatosLectura.rawQuery(
            scriptConsultaLectura, null)

        val exists = result.moveToFirst()
        val profesores = arrayListOf<Profesor>()

        if(exists){
            do {
                val cedula = result.getString(0)
                val nombre = result.getString(1)
                val esProfesorTitular = result.getInt(2)
                val esProfesorTitularBoolena = esProfesorTitular.toInt() ==1
                val sueldo = result.getDouble(3)

                if (cedula != null) {
                    val profesor = Profesor(
                        cedula,
                        nombre,
                        esProfesorTitularBoolena,
                        sueldo
                    )
                    profesores.add(profesor)
                }
            } while (result.moveToNext())
        }
        result.close()
        baseDatosLectura.close()
        return profesores
    }
}