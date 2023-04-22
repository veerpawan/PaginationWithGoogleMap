package com.pawan.paginationwithgooglemap.maps.googlemap

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ElevationViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Elevation>>()

    fun getElevation(d: String, googleMapsApiKey: String) {
        RetrofitInstance.api.getElevation(d, googleMapsApiKey).enqueue(object :
            Callback<ElevationResponse> {
            override fun onResponse(
                call: Call<ElevationResponse>,
                response: Response<ElevationResponse>
            ) {
                if (response.body() != null) {
                    movieLiveData.value = response.body()?.results
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<ElevationResponse>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }

    fun observeMovieLiveData(): MutableLiveData<List<Elevation>> {
        return movieLiveData
    }
}