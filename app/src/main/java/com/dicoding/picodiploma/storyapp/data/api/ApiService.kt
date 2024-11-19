package com.dicoding.picodiploma.storyapp.data.api

import retrofit2.http.*

interface ApiService {
//    @Multipart
//    @POST("stories/guest")
//    suspend fun uploadImage(
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//    ): FileUploadResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ) : LoginResponse

    @GET("stories")
    suspend fun getStories(): StoryResponse
}