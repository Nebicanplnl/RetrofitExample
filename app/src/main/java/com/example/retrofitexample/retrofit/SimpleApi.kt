package com.example.retrofitexample.retrofit

import com.example.retrofitexample.retrofit.model.MyPost
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("posts/1")
    suspend fun fetchPost(): Response<MyPost>
}