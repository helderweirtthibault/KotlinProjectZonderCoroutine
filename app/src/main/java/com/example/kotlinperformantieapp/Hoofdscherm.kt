package com.example.kotlinperformantieapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kotlinperformantieapp.databinding.ActivityHoofdschermBinding
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Hoofdscherm : AppCompatActivity() {
    private lateinit var binding: ActivityHoofdschermBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHoofdschermBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.netwerkButton.setOnClickListener{
            startActivity(Intent(this, Netwerkscherm::class.java))
        }

        binding.mapsButton.setOnClickListener{
            startActivity(Intent(this, MapsActivity::class.java))
        }

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_hoofdscherm)




    }
}