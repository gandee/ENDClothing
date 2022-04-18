package com.end.endclothing.api

import com.end.endclothing.model.ProductsJSONObject
import retrofit2.http.GET

/**
 * Created by GandeepanS on 18/04/2022.
 */
interface CloudApiService {
    @GET("android_test_example.json")
    suspend fun getBothHoodyAndSneakersProducts(): ProductsJSONObject
    @GET("example.json")
    suspend fun getSneakers(): ProductsJSONObject
}