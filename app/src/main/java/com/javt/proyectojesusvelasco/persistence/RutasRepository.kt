package com.javt.proyectojesusvelasco.persistence

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class RutasRepository { // SOLO UN REPOSITORIO
    private var databaseReference : DatabaseReference
    private val URL_REFERENCIA_DATABASE="https://proyectojesusvelasco-default-rtdb.europe-west1.firebasedatabase.app/"
    init {
        databaseReference= FirebaseDatabase.getInstance(URL_REFERENCIA_DATABASE).reference
    }
    fun addRuta(ruta: RutasSenderismo){
        val key=databaseReference.child("notes").push().key
        if (key != null) {
            ruta.id=key
        }
        databaseReference.child("notes").child(key!!).setValue(ruta)
    }
    fun getRutas():MutableLiveData<List<RutasSenderismo>>{
        val rutaList=MutableLiveData<List<RutasSenderismo>>()
        val firebaseDataListener=FirebaseDataListener(rutaList)
        return rutaList
    }
}