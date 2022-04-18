package com.end.endclothing.ui.state

import com.end.endclothing.model.Products

/**
 * Created by GandeepanS on 18/04/2022.
 */
sealed class EndState{

    object Idle : EndState()
    object Loading : EndState()
    data class Products(val products: List<com.end.endclothing.model.Products>) : EndState()
    data class Error(val error: String?) : EndState()
}
