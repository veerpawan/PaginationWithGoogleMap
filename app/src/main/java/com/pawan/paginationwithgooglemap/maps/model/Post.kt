package com.pawan.paginationwithgooglemap.maps.model

import com.google.gson.annotations.SerializedName

data class Post(
    @field:SerializedName("userId") val userId: Long = 0,
    @field:SerializedName("id") val id: Long = 0,
    @field:SerializedName("title") val title: String = "",
    @field:SerializedName("body") var body: String = ""
)


