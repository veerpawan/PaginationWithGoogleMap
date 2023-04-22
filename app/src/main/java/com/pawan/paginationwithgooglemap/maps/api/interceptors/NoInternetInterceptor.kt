package com.pawan.paginationwithgooglemap.maps.api.interceptors

import com.pawan.paginationwithgooglemap.maps.PaginationApp
import com.pawan.paginationwithgooglemap.maps.api.exceptions.NoNetworkException
import com.pawan.paginationwithgooglemap.maps.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NoInternetInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable(PaginationApp.getApplication())) {
            throw NoNetworkException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}