package com.javt.proyectojesusvelasco.persistence

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.javt.proyectojesusvelasco.model.RutasSenderismo

class FirebaseDataListener (var dataList: MutableLiveData<List<RutasSenderismo>>):ValueEventListener{
    override fun onDataChange(snapshot: DataSnapshot) {
        val rutas = mutableListOf<RutasSenderismo>()
        for (dataSnapshot in snapshot.children) {
            val ruta = dataSnapshot.getValue(RutasSenderismo::class.java)
            if (ruta != null) {
                rutas.add(ruta)
            }
        }
        dataList.postValue(rutas)
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e("FirebaseDataListener", "Error: ${error.message}")

    }

}