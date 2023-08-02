package com.example.testhockey.presentation.navFragment

import android.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.testhockey.business.modeles.ResultMatchesModel
import com.example.testhockey.databinding.FragmentHomeBinding
import com.example.testhockey.presentation.adapter.PastMatchesAdapter
import com.example.testhockey.presentation.adapter.TodayMatchesAdapter
import com.example.testhockey.presentation.adapter.listener.PastMatchesListener
import com.example.testhockey.viewModel.CoinsViewModel
import com.example.testhockey.viewModel.HomeViewModel


class HomeFragment : Fragment(), PastMatchesListener{
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mAdapterToday = TodayMatchesAdapter()
    private var mAdapterPast  = PastMatchesAdapter(this)

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvToday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvToday.adapter = mAdapterToday

        /*mAdapterPast = PastMatchesAdapter(object : PastMatchesListener{
            override fun pastMatches(result: ResultMatchesModel) {
                val viewModel : CoinsViewModel = ViewModelProvider(requireActivity()).get(CoinsViewModel::class.java)
                if(viewModel.myMoney >= 25){
                    viewModel.buyItem()
                    viewModel.myMoneyLiveData.observe(viewLifecycleOwner){
                        binding.tvCoins.text = it.toString()
                    }
                    println("CLICK")
                }

                Toast.makeText(context,"hfdhfkhkf", Toast.LENGTH_LONG).show()
            }

        })*/

        binding.rvPastResult.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvPastResult.adapter = mAdapterPast

        observeData()

        val viewModel : CoinsViewModel = ViewModelProvider(requireActivity()).get(CoinsViewModel::class.java)
        viewModel.myCoinsLiveData.observe(viewLifecycleOwner){
            binding.tvCoins.text = it.toString()
        }

        return binding.root
    }

    private fun observeData(){
        val viewModel : HomeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.getResultMatchesToday().observe(viewLifecycleOwner, Observer {
            mAdapterToday.setItem(it)
        })

        viewModel.getResultMatchesPast().observe(viewLifecycleOwner, Observer {
            mAdapterPast.setItem(it)
        })
    }

    override fun pastMatches(result: ResultMatchesModel) {
        val viewModel: CoinsViewModel =
            ViewModelProvider(requireActivity()).get(CoinsViewModel::class.java)


        if (viewModel.myCoins >= 25) {

            viewModel.buyItem()
            viewModel.myCoinsLiveData.observe(viewLifecycleOwner) {
                binding.tvCoins.text = it.toString()
            }
        }

        val dialog  = Dialog(requireContext(), R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(com.example.testhockey.R.layout.detailed_match)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val time : TextView = dialog.findViewById(com.example.testhockey.R.id.tv_time_details)
        val flagLeft : ImageView = dialog.findViewById(com.example.testhockey.R.id.iv_flag_left_details)
        val flagRight : ImageView = dialog.findViewById(com.example.testhockey.R.id.iv_flag_right_details)
        val result_tv : TextView = dialog.findViewById(com.example.testhockey.R.id.tv_result_details)
        val nameLeft : TextView = dialog.findViewById(com.example.testhockey.R.id.tv_name_left_details)
        val nameRight : TextView = dialog.findViewById(com.example.testhockey.R.id.tv_name_right_details)
        val countryLeft : TextView = dialog.findViewById(com.example.testhockey.R.id.tv_country_left_details)
        val countryRight : TextView = dialog.findViewById(com.example.testhockey.R.id.tv_country_right_details)
        val bt_back : ImageView = dialog.findViewById(com.example.testhockey.R.id.iv_back_home)

        time.text = result.time
        result_tv.text = result.score
        nameLeft.text = result.nameLeft
        nameRight.text = result.nameRight
        countryLeft.text = result.countryLeft
        countryRight.text = result.countryRight

        Glide.with(dialog.context)
            .load(result.flagLeft)
            .override(70, 70)
            .into(flagLeft)
        Glide.with(dialog.context)
            .load(result.flagRight)
            .override(70, 70)
            .into(flagRight)

        dialog.show()

        bt_back.setOnClickListener { dialog.cancel() }
    }
}
