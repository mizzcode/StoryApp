package com.dicoding.picodiploma.storyapp.view.story

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.picodiploma.storyapp.data.UploadRepository
import com.dicoding.picodiploma.storyapp.data.UserRepository
import com.dicoding.picodiploma.storyapp.data.local.database.ListStoryItem
import com.dicoding.picodiploma.storyapp.data.pref.UserModel
import java.io.File

class StoryViewModel(private val userRepository: UserRepository, private val uploadRepository: UploadRepository) : ViewModel() {
    var currentImageUri: Uri? = null

    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }
    fun getStories(token: String): LiveData<PagingData<ListStoryItem>> = userRepository.getStories(token).cachedIn(viewModelScope)

    fun uploadImage(file: File, description: String, token: String) = uploadRepository.uploadImage(file, description, token)
}