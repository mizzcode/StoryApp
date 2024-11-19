package com.dicoding.picodiploma.storyapp.view.signup

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.storyapp.data.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun register(name: String, email: String, password: String) = userRepository.register(name, email, password)
}