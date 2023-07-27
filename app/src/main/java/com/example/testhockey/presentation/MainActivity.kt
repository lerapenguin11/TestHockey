package com.example.testhockey.presentation

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.testhockey.databinding.ActivityMainBinding
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.testhockey.APP_ACTIVITY
import com.example.testhockey.R
import com.example.testhockey.presentation.navFragment.AssistantFragment
import com.example.testhockey.presentation.navFragment.CoinsFragment
import com.example.testhockey.presentation.navFragment.HomeFragment
import com.example.testhockey.presentation.navFragment.SelfinstructionFragment
import com.example.testhockey.utilits.replaceFragment
import com.example.testhockey.viewModel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this

        setStatusBarGradiant(this)
        //hideSystemUI()

        replaceFragment(HomeFragment())

        navigationBottom()
    }

    private fun navigationBottom() {
        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(HomeFragment())
                R.id.document -> replaceFragment(SelfinstructionFragment())
                R.id.coin -> replaceFragment(CoinsFragment())
                R.id.ass -> replaceFragment(AssistantFragment())
            }
            true
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarGradiant(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = activity.window
            val background = ContextCompat.getDrawable(activity, R.color.background)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            window.statusBarColor = ContextCompat.getColor(activity,android.R.color.transparent)
            //window.navigationBarColor = ContextCompat.getColor(activity,android.R.color)
            window.setBackgroundDrawable(background)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window,
            window.decorView.findViewById(android.R.id.content)).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}