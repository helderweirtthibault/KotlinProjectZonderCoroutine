package com.example.kotlinperformantieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinperformantieapp.databinding.FragmentNetworkCallBinding
import com.example.kotlinperformantieapp.databinding.FragmentNetworkCoroutineCallBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class NetworkCoroutineCallFragment : Fragment() {

    private val viewModel: NetworkCallViewModel by lazy {
        ViewModelProvider(this).get(NetworkCallViewModel::class.java)
    }

    private lateinit var binding: FragmentNetworkCoroutineCallBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNetworkCoroutineCallBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.callWithCoroutine();

        return binding.root
    }

}