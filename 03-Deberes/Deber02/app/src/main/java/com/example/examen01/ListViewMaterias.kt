package com.example.examen01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.examen01.database.BaseDeDatos
import com.example.examen01.modelo.Materia
import com.google.android.material.snackbar.Snackbar

class ListViewMaterias : AppCompatActivity() {
    var profesorSeleccionado = BaseDeDatos.profesorSelecciondo
    var materiasProfesor = BaseDeDatos.tablaMateria?.getMateriasProfesor(BaseDeDatos.profesorSelecciondo.cedula)
    var posicionItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_materias)
        loadMaterias()
        val nombreProfesor = findViewById<TextView>(R.id.txtNombreProfesor)
        nombreProfesor.text = profesorSeleccionado.nombre
        val botonVerProfesores= findViewById<Button>(R.id.btnVerProfesores)
        botonVerProfesores.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        val botonAnadirListView = findViewById<Button>(R.id.btnCrearMateria)
        botonAnadirListView.setOnClickListener {
            anadirMateria()
        }
        val listView = findViewById<ListView>(R.id.lvMaterias)
        registerForContextMenu(listView)

    }

    private fun loadMaterias() {
        val listView = findViewById<ListView>(
            R.id.lvMaterias
        )
        materiasProfesor = BaseDeDatos.tablaMateria?.getMateriasProfesor(BaseDeDatos.profesorSelecciondo.cedula)
        if (materiasProfesor != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                materiasProfesor!!
            )
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
            registerForContextMenu(listView)
        }
    }

    fun anadirMateria(
    ){
        irActividad(CrearMateria::class.java)
        loadMaterias()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_materia, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        BaseDeDatos.materiaSeleccionada = profesorSeleccionado.materias!!.get(posicion)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.itmEditarMateria -> {
                irActividad(EditarMateria::class.java)
                return true
            }
            R.id.itmEliminarMateria -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lvMaterias),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                eliminarMateria()
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarMateria () {
        BaseDeDatos.tablaMateria!!.eliminarMateria(BaseDeDatos.materiaSeleccionada.codigo)
        loadMaterias()
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}