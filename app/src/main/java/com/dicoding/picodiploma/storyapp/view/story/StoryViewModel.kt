package com.dicoding.picodiploma.storyapp.view.story

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.picodiploma.storyapp.data.UploadRepository
import com.dicoding.picodiploma.storyapp.data.UserRepository
import com.dicoding.picodiploma.storyapp.data.pref.UserModel
import java.io.File

class StoryViewModel(private val userRepository: UserRepository, private val uploadRepository: UploadRepository) : ViewModel() {
    var currentImageUri: Uri? = null

    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }
    fun getStories() = userRepository.getStories()

    fun uploadImage(file: File, description: String) = uploadRepository.uploadImage(file, description)
}