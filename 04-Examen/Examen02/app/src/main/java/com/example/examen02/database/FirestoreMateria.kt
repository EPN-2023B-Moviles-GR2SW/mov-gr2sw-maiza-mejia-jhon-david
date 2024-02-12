package com.example.examen02.database

import com.example.examen02.modelo.Materia
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreMateria {
    companion object{
        fun crearMateria(materia: Materia, cedulaProfesor: String){
            val db = Firebase.firestore
            val materias = db.collection("materias")

            val datosMateria = hashMapOf(
                "nombre" to materia.nombre,
                "creditos" to materia.creditos,
                "horas" to materia.horas,
                "cedulaProfesor" to cedulaProfesor
            )
            materias.document(materia.codigo).set(datosMateria)
        }

        fun consultarMaterias( listener: (ArrayList<Materia>) -> Unit)
        {
            val db = Firebase.firestore
            val arregloMaterias = arrayListOf<Materia>()
            val materiasRefUnico = db.collection("materias")

            materiasRefUnico
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {querySnapshot ->
                    // it == eso (lo que llegue)
                    for (materia in querySnapshot){
                        materia.id
                        arregloMaterias.add(anadirMateria(materia))
                    }
                    listener(arregloMaterias)
                }
                .addOnFailureListener{
                    // Errores
                }
        }


        fun anadirMateria(
            materia: QueryDocumentSnapshot
        ) : Materia {
            val nuevaMateria =  Materia(
                materia.id as String,
                materia.data.get("nombre") as String,
                materia.data.get("creditos") as Long,
                materia.data.get("horas") as Long,
                materia.data.get("cedulaProfesor") as String
            )
            return nuevaMateria
        }

        fun consultarMateria(
            codigo: String,
            onSuccess: (Materia) -> Unit
        ) {
            val db = Firebase.firestore
            val materiasRefUnica = db.collection("materias")
            materiasRefUnica
                .document(codigo)
                .get() // obtener 1 DOCUMENTO
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            val materia = Materia(
                                document.reference.id,
                                document.data?.get("nombre") as String,
                                document.data?.get("creditos") as Long,
                                document.data?.get("horas") as Long,
                                document.data?.get("cedulaProfesor") as String
                            )
                            onSuccess(materia)
                        } else {
                            //salio mal
                        }
                    } else {
                        //salio mal
                    }
                }
        }

        fun eliminarMateria(
            codigo: String
        ){
            val db = Firebase.firestore
            val materiasRefUnica = db
                .collection("materias")

            materiasRefUnica
                .document(codigo)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarMateria(
            materia: Materia
        ){
            val db = Firebase.firestore
            val materiasRefUnica = db
                .collection("materias")

            val datosActualizados = hashMapOf(
                "nombre" to materia.nombre,
                "creditos" to materia.creditos,
                "horas" to materia.horas
            )

            materiasRefUnica
                .document(materia.codigo)
                .update(datosActualizados as Map<String, Any>)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }

        fun consultarMateriasProfesor(
            cedula: String,
            listener: (ArrayList<Materia>) -> Unit
        ){
            var arregloMaterias = arrayListOf<Materia>()
            val db = Firebase.firestore
            val materiasRefUnica = db.collection("materias")
            materiasRefUnica
                .whereEqualTo("cedulaProfesor", cedula)
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (materia in querySnapshot){
                        materia.id
                        arregloMaterias.add(anadirMateria(materia))
                    }
                    listener(arregloMaterias)
                }
                .addOnFailureListener{
                    // Errores
                }
        }
    }
}