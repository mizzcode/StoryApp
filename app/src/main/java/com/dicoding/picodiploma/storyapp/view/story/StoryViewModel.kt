package com.dicoding.picodiploma.storyapp.view.story

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapp.data.UploadRepository
import com.dicoding.picodiploma.storyapp.data.UserRepository
import java.io.File

class StoryViewModel(private val userRepository: UserRepository, private val uploadRepository: UploadRepository) : ViewModel() {
    var currentImageUri: Uri? = null

    fun getStories() = userRepository.getStories()

    fun uploadImage(file: File, description: String) = uploadRepository.uploadImage(file, description)
}