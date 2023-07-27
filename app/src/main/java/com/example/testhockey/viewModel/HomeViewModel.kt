package com.example.testhockey.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testhockey.business.modeles.ResultMatchesModel
import com.example.testhockey.business.repos.ResultMatchesRepository

class HomeViewModel() : ViewModel(){

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