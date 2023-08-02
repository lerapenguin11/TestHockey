package com.example.testhockey.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testhockey.business.modeles.ResultMatchesModel
import com.example.testhockey.business.repos.ResultMatchesRepository

class HomeViewModel(application: Application) : AndroidViewModel(application){
    val teamsLiveData = MutableLiveData<ResultMatchesModel>()

    private val repo = ResultMatchesRepository()

    fun getResultMatchesToday() : LiveData<MutableList<ResultMatchesModel>>{
        val mutableData = MutableLiveData<MutableList<ResultMatchesModel>>()
        repo.getResultToday().observeForever { resultList ->
            mutableData.value = resultList
        }

        return mutableData
    }

    fun getResultMatchesPast() : LiveData<MutableList<ResultMatchesModel>>{
        val mutableData = MutableLiveData<MutableList<ResultMatchesModel>>()
        repo.getResultPast().observeForever { resultList ->
            mutableData.value = resultList
        }

        return mutableData
    }
}