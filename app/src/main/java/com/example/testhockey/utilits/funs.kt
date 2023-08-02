package com.example.testhockey.utilits

import androidx.fragment.app.Fragment
import com.example.testhockey.APP_ACTIVITY
import com.example.testhockey.R

fun replaceNavFragment(fragment: Fragment, addStack: Boolean = true) {
    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.bottom_nav,
                fragment
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.bottom_nav,
                fragment
            ).commit()
    }
}