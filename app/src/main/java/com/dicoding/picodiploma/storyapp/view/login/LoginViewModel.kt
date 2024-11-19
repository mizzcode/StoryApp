package com.dicoding.picodiploma.storyapp.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.storyapp.data.UserRepository
import com.dicoding.picodiploma.storyapp.data.pref.UserModel
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }



    fun login(email: String, password: String) = repository.login(email, password)
}