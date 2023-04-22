package com.pawan.paginationwithgooglemap.maps.api

import com.pawan.paginationwithgooglemap.maps.model.Post
import com.pawan.paginationwithgooglemap.maps.utils.NetworkResult

class PostDataSource(private val service: PostService) : NetworkCaller() {

    suspend fun selectPosts(page: Int): NetworkResult<List<Post>> {
        return getResult { service.getPosts(page) }
    }
}