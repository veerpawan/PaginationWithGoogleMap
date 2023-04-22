package com.pawan.paginationwithgooglemap.maps

import android.app.Application

class PaginationApp : Application() {

    companion object {
        private lateinit var INSTANCE: PaginationApp
        fun getApplication() = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}