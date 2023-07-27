package com.example.testhockey.presentation.navFragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testhockey.START_TIME_IN_MILLIS
import com.example.testhockey.databinding.FragmentAssistantBinding
import java.util.*

class AssistantFragment : Fragment() {
    private var _binding : FragmentAssistantBinding? = null
    private val binding get() = _binding!!
    private lateinit var countDownTimer : CountDownTimer
    private var timerRunning : Boolean = false
    private var timeLeftInMillis = START_TIME_IN_MILLIS

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAssistantBinding.inflate(inflater, container, false)

        onOffTimer()

        return binding.root
    }

    fun onOffTimer(){

        binding.tvStart.setOnClickListener {
            startTimer()
        }

        binding.tvPause.setOnClickListener {
            if(timerRunning){
                pauseTimer()
            }
        }

        binding.tvReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS
        updateCountDownText()
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        timerRunning = false
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis.toLong(), 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                timerRunning = false
            }

        }.start()
        timerRunning = true
    }

    private fun updateCountDownText() {
        val minutes : Int = ((timeLeftInMillis / 1000) / 60).toInt()
        val seconds : Int = ((timeLeftInMillis / 1000) % 60).toInt()

        val timeLeftFormatted = String.format(Locale.getDefault(),"%02d : %02d", minutes, seconds)

        binding.tvCountdown.text = timeLeftFormatted
    }
}