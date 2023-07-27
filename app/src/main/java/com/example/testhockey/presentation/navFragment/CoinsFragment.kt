package com.example.testhockey.presentation.navFragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.annotation.RequiresApi
import com.example.testhockey.R
import com.example.testhockey.databinding.FragmentCoinsBinding
import kotlin.random.Random

class CoinsFragment : Fragment(), SensorEventListener {
    private var _binding : FragmentCoinsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sensorManager: SensorManager
    private var count = 0

    private var isAnimating = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCoinsBinding.inflate(inflater, container, false)

        sensorManager = requireActivity().getSystemService(SENSOR_SERVICE) as SensorManager

        binding.tvCoins.text = count.toString()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val screenWidth = resources.displayMetrics.widthPixels.toFloat()
        val screenHeight = resources.displayMetrics.heightPixels.toFloat()
        val animatorX = ObjectAnimator.ofFloat(binding.ivCoin, "translationX", 0f, screenWidth - binding.ivCoin.width)
        val animatorY = ObjectAnimator.ofFloat(binding.ivCoin, "translationY", 0f, screenHeight - binding.ivCoin.height)

        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()

            if (acceleration > 30) {
                animateImageView(animatorX, animatorY)

                ++count
                binding.tvCoins.text = count.toString()
            }

        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    private fun animateImageView(animatorX : ObjectAnimator, animatorY : ObjectAnimator) {
        animatorX.duration = 3000
        animatorY.duration = 3000
        animatorX.interpolator = LinearInterpolator()
        animatorY.interpolator = LinearInterpolator()

        animatorX.repeatMode = ObjectAnimator.REVERSE
        animatorY.repeatMode = ObjectAnimator.REVERSE

        animatorX.repeatCount = ObjectAnimator.INFINITE
        animatorY.repeatCount = ObjectAnimator.INFINITE

        animatorX.start()
        animatorY.start()
    }
}