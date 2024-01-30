package com.example.b2023gr2sw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class HFirebaseUIAuth : AppCompatActivity() {
    private val respuestaLoginAuthUi = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){
        res: FirebaseAuthUIAuthenticationResult ->
        if(res.resultCode === RESULT_OK){
            if(res.idpResponse != null)
                seLogeo(res.idpResponse!!)
        }

    }

    fun seLogeo(
        res: IdpResponse
    ){
        val btnLogin  = findViewById<Button>(R.id.btn_login_firebase)
        val btnlLogout  = findViewById<Button>(R.id.btn_logout_firebase)
        val tvBienvenido  = findViewById<TextView>(R.id.tv_bienvenido)
        tvBienvenido.text = FirebaseAuth.getInstance().currentUser?.displayName
        btnlLogout.visibility = View.VISIBLE
        btnLogin.visibility = View.INVISIBLE
        if(res.isNewUser == true){
            registrarUsuarioPorPrimeraVez(res)
        }
    }

    fun registrarUsuarioPorPrimeraVez(usuario: IdpResponse){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hfirebase_uiauth)

        val btnLogin = findViewById<Button>(R.id.btn_login_firebase)
        btnLogin.setOnClickListener {
            val providers = arrayListOf(
                //Arreglo de Porviders para logease
                //Ej, Carreo, facebook, trit
                AuthUI.IdpConfig.EmailBuilder().build()
            )
            // construimos el intent de Login

            val logearseIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()

            // respuesta del intent
            respuestaLoginAuthUi.launch(logearseIntent)
            // https://console.firebase.google.com/u/0/project/
            // Authentication/Settings/UserAction

        }

        val btnLogout = findViewById<Button>(R.id.btn_logout_firebase)
        btnLogout.setOnClickListener {seDeslogeo()}
        val usuario = FirebaseAuth.getInstance().currentUser
        if(usuario != null){
            val btnLogin  = findViewById<Button>(R.id.btn_login_firebase)
            val btnlLogout  = findViewById<Button>(R.id.btn_logout_firebase)
            val tvBienvenido  = findViewById<TextView>(R.id.tv_bienvenido)
            tvBienvenido.text = FirebaseAuth.getInstance().currentUser?.displayName
            btnlLogout.visibility = View.VISIBLE
            btnLogin.visibility = View.INVISIBLE
            tvBienvenido.text = usuario.displayName
        }
    }

    fun seDeslogeo(){
        val btnLogin  = findViewById<Button>(R.id.btn_login_firebase)
        val btnlLogout  = findViewById<Button>(R.id.btn_logout_firebase)
        val tvBienvenido  = findViewById<TextView>(R.id.tv_bienvenido)
        tvBienvenido.text = "Bienvenido"
        btnlLogout.visibility = View.INVISIBLE
        btnLogin.visibility = View.VISIBLE
        FirebaseAuth.getInstance().signOut()
    }





}