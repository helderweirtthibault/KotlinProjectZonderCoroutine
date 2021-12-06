package com.example.kotlinperformantieapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinperformantieapp.databinding.ActivityNetwerkschermBinding

class Netwerkscherm : AppCompatActivity() {

    private lateinit var binding: ActivityNetwerkschermBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNetwerkschermBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}