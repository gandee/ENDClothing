package com.end.endclothing.api

import com.end.endclothing.model.ProductsJSONObject
import kotlinx.coroutines.flow.Flow

/**
 * Created by GandeepanS on 18/04/2022.
 */
class CloudApiHelperImpl(private val apiService: CloudApiService):CloudApiHelper {

    override suspend fun getBothHoodyAndSneakersProducts(): Flow<ProductsJSONObject> {
        TODO("Not yet implemented")
    }

    override suspend fun getSneakersProductOnly(): Flow<ProductsJSONObject> {
        TODO("Not yet implemented")
    }

}