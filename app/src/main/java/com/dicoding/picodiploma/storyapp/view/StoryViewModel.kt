package com.dicoding.picodiploma.storyapp.view

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapp.data.UserRepository

class StoryViewModel(private val repository: UserRepository) : ViewModel() {
    fun getStories() = repository.getStories()
}