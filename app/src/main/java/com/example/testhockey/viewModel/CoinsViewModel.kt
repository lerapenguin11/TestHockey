package com.example.testhockey.viewModel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CoinsViewModel(application: Application) : AndroidViewModel(application) {

    val prefs = application.getSharedPreferences(GET_PREFS_KEY, MODE_PRIVATE)

    private var myMoney = prefs.getInt(PREFS_KEY_COUNT, 0)
        set(value) {
            field = value
            myMoneyLiveData.value = value
        }

    val myMoneyLiveData = MutableLiveData(myMoney)

    var counter = 0
        set(value) {
            field = value
            moneyCounterLiveData.value = value
        }
    val moneyCounterLiveData = MutableLiveData(counter)


    fun collectMoney() {
        viewModelScope.launch {
            counter++
        }

    }

    fun buyItem() {
        myMoney -= 25
        prefs.edit()
            .putInt(PREFS_KEY_COUNT, myMoney)
            .apply()
    }

    fun saveToPrefs() {
        prefs.edit()
            .putInt(PREFS_KEY_COUNT, myMoney + counter)
            .apply()
        myMoney = prefs.getInt(PREFS_KEY_COUNT, 0)
        counter = 0
    }

    companion object {
        private const val PREFS_KEY_COUNT = "COUNT"
        private const val GET_PREFS_KEY = "KEY"
    }

}