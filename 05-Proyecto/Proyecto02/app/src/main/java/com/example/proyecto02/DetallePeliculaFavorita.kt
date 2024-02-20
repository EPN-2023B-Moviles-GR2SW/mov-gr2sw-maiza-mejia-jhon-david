package com.example.proyecto02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.google.android.material.appbar.MaterialToolbar

class DetallePeliculaFavorita : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula_favorita)

        var perfil = findViewById<LinearLayout>(R.id.ly_perfil)
        perfil.setOnClickListener{
            irActividad(Perfil::class.java)
        }

        var busqueda = findViewById<LinearLayout>(R.id.ly_busqueda)
        busqueda.setOnClickListener{
            irActividad(Busqueda::class.java)
        }

        var biblioteca = findViewById<LinearLayout>(R.id.ly_biblioteca)
        biblioteca.setOnClickListener{
            irActividad(Biblioteca::class.java)
        }

        var inicio = findViewById<LinearLayout>(R.id.ly_inicio)
        inicio.setOnClickListener{
            irActividad(Inicio::class.java)
        }

        var reseñas = findViewById<Button>(R.id.btn_reseñas)
        reseñas.setOnClickListener{
            irActividad(Resenia::class.java)
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.mtb_informacion_favorito)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}