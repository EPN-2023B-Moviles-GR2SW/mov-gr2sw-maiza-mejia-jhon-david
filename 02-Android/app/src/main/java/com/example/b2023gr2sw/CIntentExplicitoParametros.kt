package com.example.b2023gr2sw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringArrayExtra("nombre")
        val apellido = intent.getStringArrayExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton.setOnClickListener { devolverRespuesta() }
    }

    fun devolverRespuesta(){
        val intentDevovlerParametros = Intent()
        intentDevovlerParametros.putExtra("nombreModificado", "Vicente")
        intentDevovlerParametros.putExtra("addModificado", 33)
        setResult(
            RESULT_OK,
            intentDevovlerParametros
        )
        finish()
    }
}