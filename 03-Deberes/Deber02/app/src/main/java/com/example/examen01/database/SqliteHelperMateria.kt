package com.example.examen01.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen01.modelo.Materia

class SqliteHelperMateria (
    contexto: Context,
) : SQLiteOpenHelper(
    contexto,
    "materias", // nombre BDD
    null,
    1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaMateria =
            """
               CREATE TABLE Materia (
                   CODIGO VARCHAR(10) PRIMARY KEY,
                   NOMBRE VARCHAR(50),
                   CREDITOS INTEGER,
                   HORAS INTEGER,
                   CEDULA_PROFESOR VARCHAR(10),
                   FOREIGN KEY(CEDULA_PROFESOR) REFERENCES PROFESOR(CEDULA)
               )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaMateria)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int,
                           newVersion: Int) {}

    fun crearMateria(
        codigo: String,
        nombre: String,
        creditos: Int,
        horas: Int,
        idProfesor: String
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("CODIGO", codigo)
        valoresAGuardar.put("NOMBRE", nombre)
        valoresAGuardar.put("CREDITOS", creditos)
        valoresAGuardar.put("HORAS", horas)
        valoresAGuardar.put("CEDULA_PROFESOR", idProfesor)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "MATERIA", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return if (resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarMateria(codigo: String):Boolean{
        val conexionEscritura = writableDatabase
        // where codigo = ?
        val parametrosConsultaDelete = arrayOf( codigo )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "MATERIA", // Nombre tabla
                "CODIGO=?", // Consulta Where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarMeteria(
        codigo: String,
        nombre: String,
        creditos: Int,
        horas: Int,
        idProfesor: String
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("CODIGO", codigo)
        valoresAActualizar.put("NOMBRE", nombre)
        valoresAActualizar.put("CREDITOS", creditos)
        valoresAActualizar.put("HORAS", horas)
        valoresAActualizar.put("CEDULA_PROFESOR", idProfesor)
        // where ID = ?
        val parametrosConsultaActualizar = arrayOf( codigo )
        val resultadoActualizacion = conexionEscritura
            .update(
                "MATERIA", // Nombre tabla
                valoresAActualizar, // Valores
                "CODIGO=?", // Consulta Where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if(resultadoActualizacion.toInt() == -1) false else true
    }

    fun consultarMateriaPorCodigo(id: Int): Materia {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM MATERIA WHERE CODIGO = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura, // Consulta
            parametrosConsultaLectura, // Parametros
        )

        // logica busqueda
        val existeMateria = resultadoConsultaLectura.moveToFirst()
        var materiaEncontrada = Materia("M00", "" , 0, 0)
        if(existeMateria){
            do{
                val codigo= resultadoConsultaLectura.getString(0) // Indice 0
                val nombre = resultadoConsultaLectura.getString(1)
                val creditos = resultadoConsultaLectura.getInt(2)
                val horas = resultadoConsultaLectura.getInt(3)
                if(codigo != null){
                    // llenar el arreglo con un nuevo BEntrenador
                    val materia = Materia(
                        codigo,
                        nombre,
                        creditos,
                        horas)
                    materiaEncontrada = materia
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return materiaEncontrada
    }

    fun getAll(): MutableList<Materia>{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM MATERIA
        """.trimIndent()
        val result = baseDatosLectura.rawQuery(
            scriptConsultaLectura, null)

        val exists = result.moveToFirst()
        val materias = arrayListOf<Materia>()

        if(exists){
            do {
                val codigo = result.getString(0)
                val nombre = result.getString(1)
                val creditos = result.getInt(2)
                val horas = result.getInt(3)

                if (codigo != null) {
                    val materia = Materia(
                        codigo,
                        nombre,
                        creditos,
                        horas
                    )
                    materias.add(materia)
                }
            } while (result.moveToNext())
        }
        return materias
    }

    fun getMateriasProfesor (idProfesor: String): MutableList<Materia>{
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM MATERIA 
            WHERE CEDULA_PROFESOR = ?
        """.trimIndent()
        val parametrosConsultaLectura = arrayOf(idProfesor)
        val result = baseDatosLectura.rawQuery(
            scriptConsultaLectura, // Consulta
            parametrosConsultaLectura, // Parametros
        )

        val exists = result.moveToFirst()
        val materias = arrayListOf<Materia>()

        if(exists){
            do {
                val codigo = result.getString(0)
                val nombre = result.getString(1)
                val creditos = result.getInt(2)
                val horas = result.getInt(3)

                if (codigo != null) {
                    val materia = Materia(
                        codigo,
                        nombre,
                        creditos,
                        horas
                    )
                    materias.add(materia)
                }
            } while (result.moveToNext())
        }
        return materias
    }

}