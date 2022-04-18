package com.end.endclothing.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.end.endclothing.api.CloudApiHelper
import com.end.endclothing.repository.ENDClothingMainRepository
import com.end.endclothing.ui.viewmodel.ENDClothingViewModel

/**
 * Created by GandeepanS on 18/04/2022.
 */
class ViewModelFactory(private val apiHelper: CloudApiHelper) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given `Class`.
     *
     * @param modelClass a `Class` whose instance is requested
     * @return a newly created ViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ENDClothingViewModel::class.java)) {
            return ENDClothingViewModel(ENDClothingMainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}