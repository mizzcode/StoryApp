package com.dicoding.picodiploma.storyapp.di

import android.content.Context
import com.dicoding.picodiploma.storyapp.data.UploadRepository
import com.dicoding.picodiploma.storyapp.data.UserRepository
import com.dicoding.picodiploma.storyapp.data.api.ApiConfig
import com.dicoding.picodiploma.storyapp.data.local.database.StoryDatabase
import com.dicoding.picodiploma.storyapp.data.pref.UserPreference
import com.dicoding.picodiploma.storyapp.data.pref.dataStore

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        val database = StoryDatabase.getDatabase(context)
        return UserRepository.getInstance(pref, apiService, database)
    }

    fun provideUploadRepository(): UploadRepository {
        val apiService = ApiConfig.getApiService()
        return UploadRepository.getInstance(apiService)
    }
}
