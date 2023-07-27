package com.example.testhockey.presentation.navFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testhockey.databinding.FragmentHomeBinding
import com.example.testhockey.presentation.adapter.PastMatchesAdapter
import com.example.testhockey.presentation.adapter.TodayMatchesAdapter
import com.example.testhockey.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var mAdapterToday = TodayMatchesAdapter()
    private var mAdapterPast = PastMatchesAdapter()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvToday.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvToday.adapter = mAdapterToday

        binding.rvPastResult.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvPastResult.adapter = mAdapterPast

        observeData()

        return binding.root
    }

    fun observeData(){
        val viewModel : HomeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        viewModel.getResultMatchesToday().observe(viewLifecycleOwner, Observer {
            mAdapterToday.setItem(it)
            mAdapterToday.notifyDataSetChanged()
        })

        viewModel.getResultMatchesPast().observe(viewLifecycleOwner, Observer {
            mAdapterPast.setItem(it)
            mAdapterPast.notifyDataSetChanged()
        })
    }


}
