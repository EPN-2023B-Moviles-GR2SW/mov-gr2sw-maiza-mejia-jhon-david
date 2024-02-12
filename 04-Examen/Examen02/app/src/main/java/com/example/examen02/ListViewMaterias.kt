package com.example.examen02

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
import com.example.examen02.database.Firestore
import com.example.examen02.database.FirestoreMateria
import com.google.android.material.snackbar.Snackbar

class ListViewMaterias : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    var codigoMateriaSeleccionada = ""
    var cedulaProfesor = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_materias)
        loadMaterias()

        cedulaProfesor = intent.getStringExtra("cedula")!!
        val nombreProfesor = findViewById<TextView>(R.id.txtNombreProfesor)
        Firestore.consultarProfesor(cedulaProfesor!!){
            nombreProfesor.text = it.nombre
        }

        val botonVerProfesores= findViewById<Button>(R.id.btnVerProfesores)
        botonVerProfesores.setOnClickListener {
            irActividad(MainActivity::class.java, "0")
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
        println(cedulaProfesor + " cedula")
        cedulaProfesor = intent.getStringExtra("cedula")!!
        FirestoreMateria.consultarMateriasProfesor(cedulaProfesor!!) {
            println(it.size)
            if (it != null) {
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    it!!
                )
                listView.adapter = adapter
                adapter.notifyDataSetChanged()
                registerForContextMenu(listView)
            }
        }
    }

    fun anadirMateria(
    ){
        cedulaProfesor = intent.getStringExtra("cedula")!!
        irActividad(CrearMateria::class.java, cedulaProfesor!!)
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
        cedulaProfesor = intent.getStringExtra("cedula")!!
        FirestoreMateria.consultarMateriasProfesor(cedulaProfesor) { codigoMateriaSeleccionada = it[posicionItemSeleccionado].codigo }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.itmEditarMateria -> {
                irActividad(EditarMateria::class.java, codigoMateriaSeleccionada)
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
        FirestoreMateria.eliminarMateria(codigoMateriaSeleccionada)
        loadMaterias()
    }

    fun irActividad (
        clase: Class <*>, id: String
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}