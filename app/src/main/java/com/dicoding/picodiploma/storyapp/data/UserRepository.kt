package com.dicoding.picodiploma.storyapp.data

import androidx.lifecycle.liveData
import com.dicoding.picodiploma.storyapp.data.api.ApiService
import com.dicoding.picodiploma.storyapp.data.api.LoginResponse
import com.dicoding.picodiploma.storyapp.data.api.RegisterResponse
import com.dicoding.picodiploma.storyapp.data.api.StoryResponse
import com.dicoding.picodiploma.storyapp.data.pref.UserModel
import com.dicoding.picodiploma.storyapp.data.pref.UserPreference
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    fun register(name: String, email: String, password: String) = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.registerUser(name, email, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(Result.Error(errorResponse.message ?: "Terjadi Kesalahan"))
        }
    }

    fun login(email: String, password: String) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(Result.Error(errorResponse.message ?: "Terjadi Kesalahan"))
        }
    }

    fun getStories() = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.getStories()
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, StoryResponse::class.java)
            emit(Result.Error(errorResponse.message ?: "Terjadi Kesalahan"))
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}