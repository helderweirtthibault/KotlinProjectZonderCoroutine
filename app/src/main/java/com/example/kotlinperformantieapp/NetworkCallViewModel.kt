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
class NetworkCallViewModel(private val dispatcher: CoroutineDispatcher) : ViewModel() {

    constructor():this(Dispatchers.IO)



    private var lijst = arrayListOf<String>(
            "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=19d9efa32424ab80a516e54af04e0b9d",
    "http://api.weatherstack.com/current?access_key=50f1811cde203510e723a87074312b96&query=New%20York",
    "https://api.weatherapi.com/v1/current.json?key=8b356f580be9479cb30162113210612&q=Paris&aqi=no")

    private var x = Job()
    private val jobCoScopeVanX = CoroutineScope(x + Dispatchers.IO)

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    private val _response2 = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response2: LiveData<String>
        get() = _response

    private val _response3 = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response3: LiveData<String>
        get() = _response



    init {
        callRunNetCall()
    }

    fun callRunNetCall(){
        runNetCall("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=19d9efa32424ab80a516e54af04e0b9d")
        runNetCall("http://api.weatherstack.com/current?access_key=50f1811cde203510e723a87074312b96&query=New%20York")
        runNetCall("https://api.weatherapi.com/v1/current.json?key=8b356f580be9479cb30162113210612&q=Paris&aqi=no")
    }

     private fun runNetCall(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if(url.contains("openweather")){
                    _response.postValue(response.body()?.string())
                }else if(url.contains("weatherstack")){
                    _response2.postValue(response.body()?.string())
                }else if (url.contains("weatherapi")){
                    _response3.postValue(response.body()?.string())
                }
            }
        })
    }

    fun callWithCoroutine(){
        jobCoScopeVanX.launch {
            withContext(Dispatchers.IO){
                runSuspendNetCall("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=19d9efa32424ab80a516e54af04e0b9d")
                runSuspendNetCall("http://api.weatherstack.com/current?access_key=50f1811cde203510e723a87074312b96&query=New%20York")
                runSuspendNetCall("https://api.weatherapi.com/v1/current.json?key=8b356f580be9479cb30162113210612&q=Paris&aqi=no")
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
                if(url.contains("openweather")){
                    _response.postValue(response.body()?.string())
                }else if(url.contains("weatherstack")){
                    _response2.postValue(response.body()?.string())
                }else if (url.contains("weatherapi")){
                    _response3.postValue(response.body()?.string())
                }
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
        runNetCallTest("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=19d9efa32424ab80a516e54af04e0b9d")
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
            runSuspendNetCallTest("http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=19d9efa32424ab80a516e54af04e0b9d")
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

