package com.end.endclothing.api

import com.end.endclothing.model.ProductsJSONObject
import kotlinx.coroutines.flow.Flow

/**
 * Created by GandeepanS on 18/04/2022.
 */
interface CloudApiHelper {

    suspend fun getBothHoodyAndSneakersProducts(): Flow<ProductsJSONObject>
    suspend fun getSneakersProductOnly(): Flow<ProductsJSONObject>
}