package com.pawan.paginationwithgooglemap.maps.api

import com.pawan.paginationwithgooglemap.maps.api.interceptors.NoInternetInterceptor
import com.pawan.paginationwithgooglemap.maps.model.Post
import com.pawan.paginationwithgooglemap.maps.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {

    @GET("posts")
    suspend fun getPosts(@Query("_page") page: Int): Response<List<Post>>

    companion object {

        fun create(): PostService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(NoInternetInterceptor())
                .build()
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostService::class.java)
        }
    }
}