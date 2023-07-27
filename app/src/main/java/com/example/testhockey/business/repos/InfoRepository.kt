package com.example.testhockey.business.repos

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testhockey.business.modeles.SelfinstructionModel
import com.google.firebase.firestore.FirebaseFirestore

class InfoRepository {

    @SuppressLint("SuspiciousIndentation")
    fun getInfo() : LiveData<MutableList<SelfinstructionModel>> {
        val mutableData = MutableLiveData<MutableList<SelfinstructionModel>>()
        FirebaseFirestore.getInstance().collection("infoHockey").get()
            .addOnSuccessListener { _result ->
                val listData = mutableListOf<SelfinstructionModel>()
                for (document in _result) {
                    val title = document.getString("title")
                    val text = document.getString("text")
                    val icon = document.getString("icon")

                    val infoModel = SelfinstructionModel(
                        title = title!!,
                        text = text!!,
                        icon = icon!!
                    )
                    listData.add(infoModel)
                }
                mutableData.value = listData
                Log.i("SNAPSHOT: ", mutableData.value.toString())
            }

        return mutableData
    }
}