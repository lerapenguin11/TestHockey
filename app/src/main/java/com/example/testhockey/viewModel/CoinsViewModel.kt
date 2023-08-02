package com.example.testhockey.viewModel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CoinsViewModel(application: Application) : AndroidViewModel(application) {

    val pref = application.getSharedPreferences(GET_PREFS_KEY, MODE_PRIVATE)

    var myCoins = pref.getInt(PREFS_KEY_COUNT, 0)
        set(value) {
            field = value
            myCoinsLiveData.value = value
        }
    val myCoinsLiveData = MutableLiveData(myCoins)

    var counter = 0
        set(value) {
            field = value
            coinsCounterLiveData.value = value
        }
    val coinsCounterLiveData = MutableLiveData(counter)

    fun buyItem() {
        myCoins -= 25
        pref.edit()
            .putInt(PREFS_KEY_COUNT, myCoins)
            .apply()
    }

    fun collectCoins() {
        viewModelScope.launch {
            counter++
        }

    }

    fun saveToPrefs() {
        pref.edit()
            .putInt(PREFS_KEY_COUNT, myCoins + counter)
            .apply()
        myCoins = pref.getInt(PREFS_KEY_COUNT, 0)
        counter = 0
    }

    companion object {
        private const val PREFS_KEY_COUNT = "COUNT"
        private const val GET_PREFS_KEY = "KEY"
    }
}