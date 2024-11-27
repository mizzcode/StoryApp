package com.dicoding.picodiploma.storyapp.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.storyapp.data.UserRepository
import com.dicoding.picodiploma.storyapp.data.pref.UserModel

class DetailViewModel(private val repository: UserRepository) : ViewModel() {
    fun getDetailStory(id: String, token: String) = repository.getDetailStory(id, token)

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}