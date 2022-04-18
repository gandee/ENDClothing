package com.end.endclothing.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = "https://www.endclothing.com/media/catalog/"
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    val cloudApiService: CloudApiService = getRetrofit().create(CloudApiService::class.java)

}