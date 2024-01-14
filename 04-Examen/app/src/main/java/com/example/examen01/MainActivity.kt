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
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    val arregloProfesores = BaseDatosMemoria.arregloProfesores
    val arregloMaterias = BaseDatosMemoria.arregloMateriasModelo
    var posicionItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lvMainActivity)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1,
            arregloProfesores
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btnCrearProfesor)
        botonAnadirListView.setOnClickListener {
            anadirProfesor(adaptador)
        }
        registerForContextMenu(listView)
    }

    fun anadirProfesor(
        adaptador: ArrayAdapter<Profesor>
    ){
        irActividad(CrearProsefor::class.java)
        adaptador.notifyDataSetChanged()
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
        BaseDatosMemoria.profesorSelecciondo = arregloProfesores[posicion]
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
        val listView = findViewById<ListView>(R.id.lvMainActivity)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1,
            arregloProfesores
        )
        listView.adapter = adaptador
        arregloProfesores.remove(BaseDatosMemoria.profesorSelecciondo)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}

