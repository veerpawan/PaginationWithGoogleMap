package com.pawan.paginationwithgooglemap.maps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pawan.paginationwithgooglemap.maps.data.repository.PostRepository

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val repository: PostRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostsViewModel(repository) as T
        }
        throw IllegalArgumentException("Exception: Unknown ViewModel class")
    }
}