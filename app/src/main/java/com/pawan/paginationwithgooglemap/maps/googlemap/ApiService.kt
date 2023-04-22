package com.pawan.paginationwithgooglemap.maps.googlemap

import com.google.android.gms.maps.model.LatLng
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/maps/api/elevation/json")
    fun getElevation(@Query("locations") locations: String, @Query("key") key: String): Call<ElevationResponse>
}