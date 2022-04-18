package com.end.endclothing.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.end.endclothing.model.Products
import com.end.endclothing.repository.ENDClothingMainRepository
import com.end.endclothing.ui.intent.ENDIntent
import com.end.endclothing.ui.state.EndState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by GandeepanS on 18/04/2022.
 */
class ENDClothingViewModel(
    private val repository: ENDClothingMainRepository
) : ViewModel() {

    val userIntent = Channel<ENDIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<EndState>(EndState.Idle)
    val state: StateFlow<EndState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is ENDIntent.getCombineProducts -> getCombineproducts()
                }
            }
        }
    }

    private fun getCombineproducts() {
        viewModelScope.launch {
            _state.value = EndState.Loading
            _state.value = try {

                val combineProducts = mutableListOf<Products>()
                repository.getBothHoodyAndSneakersProducts()
                    .zip(repository.getSneakersProductOnly()) { hoodyAndSneakers, sneakersOnly ->

                        combineProducts.addAll(hoodyAndSneakers.productObject.sortedByDescending { it.id })
                        combineProducts.addAll(sneakersOnly.productObject)
                        return@zip combineProducts
                    }
                    .flowOn(Dispatchers.Default)
                    .catch { e ->
                    }
                    .collect {

                    }
                EndState.Products(combineProducts)
            } catch (e: Exception) {
                EndState.Error(e.localizedMessage)
            }

        }
    }

}