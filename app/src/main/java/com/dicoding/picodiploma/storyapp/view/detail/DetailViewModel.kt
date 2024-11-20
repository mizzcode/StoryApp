package com.dicoding.picodiploma.storyapp.view.detail

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapp.data.UserRepository

class DetailViewModel(private val repository: UserRepository) : ViewModel() {
    fun getDetailStory(id: String) = repository.getDetailStory(id)
}