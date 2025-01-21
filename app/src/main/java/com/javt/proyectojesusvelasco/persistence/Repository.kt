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
    fun addRuta(ruta: RutasSenderismo){
        val key=databaseReference.child("rutas").push().key
        if (key != null) {
            ruta.id=key
        }
        databaseReference.child("rutas").child(key!!).setValue(ruta)
    }
    fun addUsuario(usuario: Usuario) {
        val key = databaseReference.child("usuarios").push().key
        if (key != null) {
            databaseReference.child("usuarios").child(key).setValue(usuario)
        } else {
            Log.e("Firebase", "Error al insertar")
        }
    }
    fun loginUsuario(nombreUsuario: String, contrasena: String, onResult: (Boolean, String?) -> Unit) {
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
                                onResult(true, "Login exitoso")
                            } else {
                                Log.e("Login", "Contraseña incorrecta")
                                onResult(false, "Contraseña incorrecta")
                            }
                        } else {
                            Log.e("Login", "Usuario no encontrado en el snapshot")
                            onResult(false, "Usuario no encontrado")
                        }
                    }
                } else {
                    Log.e("Login", "No existe ningún usuario con ese nombre")
                    onResult(false, "Usuario no encontrado")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Login", "Error al realizar la consulta: ${exception.message}")
                onResult(false, "Error al acceder a la base de datos: ${exception.message}")
            }
    }

    fun registrarUsuario(nombreUsuario: String, contrasena: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("usuarios")
        // Crear el usuario
        val usuario = Usuario(nombreUsuario, contrasena)
        // Guardar el usuario con su nombre de usuario como clave
        databaseReference.child(nombreUsuario).setValue(usuario)
            .addOnSuccessListener {
                Log.d("Registro", "Usuario registrado con éxito")
            }
            .addOnFailureListener { exception ->
                Log.e("Registro", "Error al registrar usuario: ${exception.message}")
            }
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
}