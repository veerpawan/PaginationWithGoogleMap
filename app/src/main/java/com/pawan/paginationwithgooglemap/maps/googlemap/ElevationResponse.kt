package com.pawan.paginationwithgooglemap.maps.googlemap

data class ElevationResponse(
    val results: List<Elevation>,
    val status: String?
)