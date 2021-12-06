package com.example.kotlinperformantieapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinperformantieapp.databinding.FragmentNetworkCallBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException


class NetworkCallFragment : Fragment() {

    private var x = Job()
    private val jobCoScopeVanX = CoroutineScope(x + Dispatchers.IO)

    private val viewModel: NetworkCallViewModel by lazy {
        ViewModelProvider(this).get(NetworkCallViewModel::class.java)
    }

    private lateinit var binding : FragmentNetworkCallBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNetworkCallBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


//        // Inflate the layout for this fragment
//        jobCoScopeVanX.launch(Dispatchers.IO) {
//        }
        return binding.root
    }


}