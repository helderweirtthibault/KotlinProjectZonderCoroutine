package com.example.kotlinperformantieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class CryptoCallViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    constructor():this(Dispatchers.IO)

    var url = "http://api2.binance.com/api/v3/ticker/24hr"

    private var x = Job()
    private val jobCoScopeVanX = CoroutineScope(x + dispatcher)

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response


    fun callRunNetCall(){
        runNetCall(url)
    }

     private fun runNetCall(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                _response.postValue(response.body()?.string())
            }
        })
    }

    fun callWithCoroutine(){
        jobCoScopeVanX.launch {
            withContext(Dispatchers.IO){
                delay(3000)
                runNetCall(url)
            }
        }
    }

    suspend fun runSuspendNetCall(url: String){
        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                _response.postValue(response.body()?.string())
            }
        })
    }

    //test data en methoden
    //
    //
    private var isSessionExpired = false

    suspend fun checkSessionExpiry(): Boolean {
        withContext(Dispatchers.IO) {
            delay(5000)
            isSessionExpired = true
        }
        return isSessionExpired
    }

    //data voor niet coroutine test
    private var userDataZonderCo: MutableLiveData<Any> = MutableLiveData<Any>()
    val userDataLiveZonderCo: LiveData<Any> = userDataZonderCo

    //data voor coroutine test
    private var _userDataLiveMetCo: MutableLiveData<Any> = MutableLiveData<Any>()
    val userDataLive: LiveData<Any> = _userDataLiveMetCo

    fun saveSessionData() {
        runNetCallTest(url)
    }

    private fun runNetCallTest(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                userDataZonderCo.postValue(response.body()?.string())
            }
        })
    }

    suspend fun saveSessionLiveData() {
        viewModelScope.launch(dispatcher) {
            runSuspendNetCallTest(url)
        }
    }

    suspend fun runSuspendNetCallTest(url: String){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                _userDataLiveMetCo.postValue(response.body()?.string())
            }
        })
    }
}

