package com.pawan.paginationwithgooglemap.maps.data.repository

import com.pawan.paginationwithgooglemap.maps.api.PostDataSource
import com.pawan.paginationwithgooglemap.maps.model.Post
import com.pawan.paginationwithgooglemap.maps.utils.NetworkResult


class PostRepository(private val remote: PostDataSource) {
    suspend fun fetchPosts(page: Int): List<Post> {
        return when (val response = remote.selectPosts(page)) {
            is NetworkResult.Success -> response.data
            is NetworkResult.Error -> throw response.exception
        }
    }
}