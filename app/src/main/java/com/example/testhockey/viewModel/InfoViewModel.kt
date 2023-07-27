package com.example.testhockey.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testhockey.business.modeles.ResultMatchesModel
import com.example.testhockey.business.modeles.SelfinstructionModel
import com.example.testhockey.business.repos.InfoRepository

class InfoViewModel : ViewModel() {

    private val repo = InfoRepository()

    fun getInfo() : LiveData<MutableList<SelfinstructionModel>> {
        val mutableData = MutableLiveData<MutableList<SelfinstructionModel>>()
        repo.getInfo().observeForever { resultList ->
            mutableData.value = resultList
        }
        return mutableData
    }
}