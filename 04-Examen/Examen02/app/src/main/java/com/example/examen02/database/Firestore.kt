package com.example.examen02.database

import android.widget.ArrayAdapter
import com.example.examen02.modelo.Materia
import com.example.examen02.modelo.Profesor
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Firestore {

    companion object{
        fun crearProfesor(profesor: Profesor){
            val db = Firebase.firestore
            val profesores = db.collection("profesores")

            val datosProfesor = hashMapOf(
                "nombre" to profesor.nombre,
                "esProfesorTitular" to profesor.esProfesorTitular,
                "sueldo" to profesor.sueldo,
            )
            profesores.document(profesor.cedula).set(datosProfesor)
        }

        fun consultarPrefesores( listener: (ArrayList<Profesor>) -> Unit)
        {
            val db = Firebase.firestore
            val arregloProfesores = arrayListOf<Profesor>()
            val profesoresRefUnico = db.collection("profesores")

            profesoresRefUnico
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {querySnapshot ->
                    // it == eso (lo que llegue)
                    for (profesor in querySnapshot){
                        profesor.id
                        arregloProfesores.add(anadirProfesor(profesor))
                    }
                    listener(arregloProfesores)
                }
                .addOnFailureListener{
                    // Errores
                }
        }


        fun anadirProfesor(
            profesor: QueryDocumentSnapshot
        ) : Profesor {
            val nuevoPrefesor =  Profesor(
                profesor.id as String,
                profesor.data.get("nombre") as String,
                profesor.data.get("esProfesorTitular") as Boolean,
                profesor.data.get("sueldo") as Long,
                profesor.data.get("materias") as ArrayList<Materia>?,
            )
            return nuevoPrefesor
        }

        fun consultarProfesor(
            cedula: String,
            listener: (Profesor) -> Unit,
        ) {
            val db = Firebase.firestore
            val profesoreesRefUnica = db.collection("profesores")
            profesoreesRefUnica
                .document(cedula)
                .get() // obtener 1 DOCUMENTO
                .addOnSuccessListener { querySnapshot ->
                    val document = querySnapshot
                    val profesor = Profesor(
                        document.reference.id,
                        document.data?.get("nombre") as String,
                        document.data?.get("esProfesorTitular") as Boolean,
                        document.data?.get("sueldo") as Long,)
                    listener(profesor)
                }
                .addOnFailureListener{
                    // Errores
                }
        }

        fun eliminarProfesor(
            cedula: String
        ){
            val db = Firebase.firestore
            val profesoresRefUnica = db
                .collection("profesores")

            profesoresRefUnica
                .document(cedula)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarProfesor(
            profesor: Profesor
        ){
            val db = Firebase.firestore
            val profesoresRefUnica = db
                .collection("profesores")


            val datosActualizados = hashMapOf(
                "nombre" to profesor.nombre,
                "esProfesorTitular" to profesor.esProfesorTitular,
                "sueldo" to profesor.sueldo,
                "materias" to listOf("west_coast", "norcal"),
            )

            profesoresRefUnica
                .document(profesor.cedula)
                .update(datosActualizados)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }
    }
}