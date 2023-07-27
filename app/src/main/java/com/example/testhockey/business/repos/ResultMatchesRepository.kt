package com.example.testhockey.business.repos

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testhockey.business.modeles.ResultMatchesModel
import com.google.firebase.firestore.FirebaseFirestore

class ResultMatchesRepository {

    @SuppressLint("SuspiciousIndentation")
    fun getResultToday() : LiveData<MutableList<ResultMatchesModel>>{
        val mutableData = MutableLiveData<MutableList<ResultMatchesModel>>()
            FirebaseFirestore.getInstance().collection("resultMatches").get()
                .addOnSuccessListener { _result ->
                    val listData = mutableListOf<ResultMatchesModel>()
                    for (document in _result) {
                        val time = document.getString("time")
                        val score = document.getString("score")
                        val flagLeft = document.getString("flagLeft")
                        val flagRight = document.getString("flagRight")
                        val countryLeft = document.getString("countryLeft")
                        val countryRight = document.getString("countryRight")
                        val nameLeft = document.getString("nameLeft")
                        val nameRight = document.getString("nameRight")
                        val resultMatchesModel = ResultMatchesModel(
                            time = time!!,
                            score = score!!,
                            flagLeft = flagLeft!!,
                            flagRight = flagRight!!,
                            countryLeft = countryLeft!!,
                            countryRight = countryRight!!,
                            nameLeft = nameLeft!!,
                            nameRight = nameRight!!
                        )
                        listData.add(resultMatchesModel)
                    }
                    mutableData.value = listData
                    Log.i("SNAPSHOT: ", mutableData.value.toString())
                }

            return mutableData

    }

    @SuppressLint("SuspiciousIndentation")
    fun getResultPast() : LiveData<MutableList<ResultMatchesModel>>{
        val mutableData = MutableLiveData<MutableList<ResultMatchesModel>>()
        FirebaseFirestore.getInstance().collection("resultMatchesPast").get()
            .addOnSuccessListener { _result ->
                val listData = mutableListOf<ResultMatchesModel>()
                for (document in _result) {
                    val time = document.getString("time")
                    val date = document.getString("date")
                    val flagLeft = document.getString("flagLeft")
                    val flagRight = document.getString("flagRight")
                    val nameLeft = document.getString("nameLeft")
                    val nameRight = document.getString("nameRight")
                    val countryLeft = document.getString("countryLeft")
                    val countryRight = document.getString("countryRight")
                    val result = document.getString("score")
                    val resultMatchesModel = ResultMatchesModel(
                        time = time!!,
                        date = date!!,
                        flagLeft = flagLeft!!,
                        flagRight = flagRight!!,
                        nameLeft = nameLeft!!,
                        nameRight = nameRight!!,
                        countryLeft = countryLeft!!,
                        countryRight = countryRight!!,
                        score = result!!
                    )
                    listData.add(resultMatchesModel)
                }
                mutableData.value = listData
                Log.i("SNAPSHOT: ", mutableData.value.toString())
            }

        return mutableData

    }
}