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
import androidx.appcompat.app.AlertDialog
import com.example.examen02.database.Firestore
import com.example.examen02.modelo.Profesor
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var posicionItemSeleccionado = 0
    var cedulaProfesorSeleccionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadProfesores()
        val botonAnadirListView = findViewById<Button>(R.id.btnCrearProfesor)
        botonAnadirListView.setOnClickListener {
            anadirProfesor()
        }
    }

    private fun loadProfesores() {
        val listView = findViewById<ListView>(
            R.id.lvMainActivity
        )
        Firestore.consultarPrefesores {
            if (it!= null) {
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

    fun anadirProfesor(){
        irActividad(CrearProsefor::class.java)
        loadProfesores()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        Firestore.consultarPrefesores { cedulaProfesorSeleccionado = it[posicion].cedula }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.miEditar -> {
                irActividad(EditarProfesor::class.java)
                return true
            }
            R.id.miEliminar -> {
                abrirDialogo()
                return true
            }
            R.id.miVerMaterias -> {
                irActividad(ListViewMaterias::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lvMainActivity),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                eliminarProfesor()
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }
    fun eliminarProfesor () {
        mostrarSnackbar(cedulaProfesorSeleccionado)
        Firestore.eliminarProfesor(cedulaProfesorSeleccionado)
        loadProfesores()
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        intent.putExtra("cedula", cedulaProfesorSeleccionado)
        startActivity(intent)
    }

}

