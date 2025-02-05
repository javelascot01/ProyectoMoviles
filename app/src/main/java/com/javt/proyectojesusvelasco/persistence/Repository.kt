package com.javt.proyectojesusvelasco.persistence

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.javt.proyectojesusvelasco.model.RutasSenderismo
import com.javt.proyectojesusvelasco.model.Usuario
import kotlin.collections.getValue

class Repository { // SOLO UN REPOSITORIO
    private var databaseReference : DatabaseReference
    private val URL_REFERENCIA_DATABASE="https://proyectojesusvelasco-default-rtdb.europe-west1.firebasedatabase.app/"
    init {
        databaseReference= FirebaseDatabase.getInstance(URL_REFERENCIA_DATABASE).reference
    }
    /**
     *          METODOS DE RUTAS
     */
    /**
     * Método que permite añadir una ruta a la base de datos
     */
    fun addRuta(ruta: RutasSenderismo){
        val key=databaseReference.child("rutas").push().key
        if (key != null) {
            ruta.id=key
        }
        databaseReference.child("rutas").child(key!!).setValue(ruta)
    }
    fun getRutas():MutableLiveData<List<RutasSenderismo>>{
        val rutaList=MutableLiveData<List<RutasSenderismo>>()
        val firebaseDataListener= object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("RutasRecibidas", snapshot.toString())
                val rutas = mutableListOf<RutasSenderismo>()
                for (data in snapshot.children) {
                    val ruta = data.getValue(RutasSenderismo::class.java)
                    if (ruta != null) {
                        rutas.add(ruta)
                        Log.d("RutasRecibidas", ruta.toString())
                    }
                }
                rutaList.value = rutas
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error
            }
        }
        databaseReference.child("rutas").addValueEventListener(firebaseDataListener)
        Log.d("RutasRecibidas", rutaList.value.toString())
        return rutaList
    }

    /**
     *          METODOS DE USUARIOS
     */


    /**
     * Método que permite loguear a un usuario en la aplicación
     */
    fun loginUsuario(nombreUsuario: String, contrasena: String, onResult: (Boolean, String?, String?) -> Unit) {
        val databaseReference = FirebaseDatabase.getInstance(URL_REFERENCIA_DATABASE).getReference("usuarios")

        Log.e("Login", "Consultando usuario: $nombreUsuario")

        // Realizamos la consulta para buscar al usuario por nombreUsuario
        databaseReference.orderByChild("nombreUsuario").equalTo(nombreUsuario).get()
            .addOnSuccessListener { snapshot ->
                Log.e("Login", "Consulta realizada con éxito")

                if (snapshot.exists()) {
                    Log.e("Login", "Usuario encontrado, verificando contraseña")

                    // Iteramos sobre los resultados para obtener el usuario correspondiente
                    snapshot.children.firstOrNull()?.let { userSnapshot ->
                        val usuario = userSnapshot.getValue(Usuario::class.java)
                        if (usuario != null) {
                            // Comparamos la contraseña
                            if (usuario.contrasena == contrasena) {
                                Log.e("Login", "Login exitoso")
                                val userKey = userSnapshot.key // Obtenemos la clave del usuario
                                onResult(true, "Login exitoso", userKey)
                            } else {
                                Log.e("Login", "Contraseña incorrecta")
                                onResult(false, "Contraseña incorrecta", null)
                            }
                        } else {
                            Log.e("Login", "Usuario no encontrado en el snapshot")
                            onResult(false, "Usuario no encontrado", null)
                        }
                    }
                } else {
                    Log.e("Login", "No existe ningún usuario con ese nombre")
                    onResult(false, "Usuario no encontrado", null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Login", "Error al realizar la consulta: ${exception.message}")
                onResult(false, "Error al acceder a la base de datos: ${exception.message}", null)
            }
    }

    fun actualizarNombreUsuario(idUsuario: String, nuevoNombreUsuario: String, onResult: (Boolean) -> Unit) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("usuarios")
        val userRef = databaseReference.child(idUsuario)
        userRef.child("nombreUsuario").setValue(nuevoNombreUsuario)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Repository", "Nombre de usuario actualizado exitosamente.")
                    onResult(true)
                } else {
                    Log.e("Repository", "Error al actualizar el nombre de usuario.", task.exception)
                    onResult(false)
                }
            }
    }

    /**
     * Método que permite añadir un usuario a la base de datos
     */
    fun addUsuario(usuario: Usuario) {
        val key = databaseReference.child("usuarios").push().key
        if (key != null) {
            databaseReference.child("usuarios").child(key).setValue(usuario)
        } else {
            Log.e("Firebase", "Error al insertar")
        }
    }

}