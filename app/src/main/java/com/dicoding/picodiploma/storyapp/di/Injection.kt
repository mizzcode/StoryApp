package com.dicoding.picodiploma.storyapp.di

import android.content.Context
import com.dicoding.picodiploma.storyapp.data.UploadRepository
import com.dicoding.picodiploma.storyapp.data.UserRepository
import com.dicoding.picodiploma.storyapp.data.api.ApiConfig
import com.dicoding.picodiploma.storyapp.data.api.ApiService
import com.dicoding.picodiploma.storyapp.data.pref.UserPreference
import com.dicoding.picodiploma.storyapp.data.pref.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    private fun provideApiService(context: Context): ApiService {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        return ApiConfig.getApiService(user.token)
    }

    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref, provideApiService(context))
    }

    fun provideUploadRepository(context: Context): UploadRepository {
        return UploadRepository.getInstance(provideApiService(context))
    }
}
