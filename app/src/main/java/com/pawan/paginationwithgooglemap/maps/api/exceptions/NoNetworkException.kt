package com.pawan.paginationwithgooglemap.maps.api.exceptions

import java.io.IOException


class NoNetworkException : IOException() {
    override val message: String
        get() = "Not Internet connection available"
}