package com.example.kotlinperformantieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class NetworkCallViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    init {
        runNetCall("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=19d9efa32424ab80a516e54af04e0b9d")
    }

    fun runNetCall(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) { _response.postValue(response.body()?.string()) }
        })
    }

//    /**
//     * Sets the value of the response LiveData to the Mars API status or the successful number of
//     * Mars properties retrieved.
//     */
//    private fun getMarsRealEstateProperties() {
//        MarsApi.retrofitService.getProperties().enqueue( object: Callback<String> {
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//            }
//
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                _response.value = response.body()
//            }
//        })
//    }
}