package com.end.endclothing.repository

import com.end.endclothing.api.CloudApiHelper


class ENDClothingMainRepository(private val cloudApiHelper: CloudApiHelper) {

    suspend fun getBothHoodyAndSneakersProducts()= cloudApiHelper.getBothHoodyAndSneakersProducts()
    suspend fun getSneakersProductOnly() = cloudApiHelper.getSneakersProductOnly()
}