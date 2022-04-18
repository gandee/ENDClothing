package com.end.endclothing.api

import com.end.endclothing.model.ProductsJSONObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by GandeepanS on 18/04/2022.
 */
class CloudApiHelperImpl(private val apiService: CloudApiService):CloudApiHelper {

    override suspend fun getBothHoodyAndSneakersProducts(): Flow<ProductsJSONObject> {
       return flow { emit(apiService.getBothHoodyAndSneakersProducts()) }
    }

    override suspend fun getSneakersProductOnly(): Flow<ProductsJSONObject> {
        return flow { emit(apiService.getSneakers()) }
    }

}