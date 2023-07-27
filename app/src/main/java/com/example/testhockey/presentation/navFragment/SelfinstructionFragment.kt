package com.example.testhockey.presentation.navFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testhockey.R
import com.example.testhockey.databinding.FragmentSelfinstructionBinding
import com.example.testhockey.presentation.adapter.SelfInstructionAdapter
import com.example.testhockey.viewModel.HomeViewModel
import com.example.testhockey.viewModel.InfoViewModel

class SelfinstructionFragment : Fragment() {
    private var _binding : FragmentSelfinstructionBinding? = null
    private val binding get() = _binding!!
    private var mAdapterInfo = SelfInstructionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSelfinstructionBinding.inflate(inflater, container, false)

        binding.rvHockeyReferenceGuide.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvHockeyReferenceGuide.adapter = mAdapterInfo

        observeData()

        return binding.root
    }

    private fun observeData() {
        val viewModel : InfoViewModel = ViewModelProvider(requireActivity()).get(InfoViewModel::class.java)
        viewModel.getInfo().observe(viewLifecycleOwner, Observer {
            mAdapterInfo.setItem(it)
            mAdapterInfo.notifyDataSetChanged()
        })

    }
}