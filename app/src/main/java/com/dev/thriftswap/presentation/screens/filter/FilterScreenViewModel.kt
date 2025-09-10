package com.dev.thriftswap.presentation.screens.filter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dev.thriftswap.data.model.FilterOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class FilterScreenViewModel @Inject constructor() :
ViewModel(){
    private val _filterOptions = mutableStateOf(FilterOptions())
    val filterOptions: MutableState<FilterOptions> = _filterOptions

    fun updateGender(gender: String) {
        _filterOptions.value = _filterOptions.value.copy(gender = gender)
    }

    fun updateSize(size: String) {
        _filterOptions.value = _filterOptions.value.copy(size = size)
    }

    fun updateColor(color: String) {
        _filterOptions.value = _filterOptions.value.copy(color = color)
    }

    fun updateBrand(brand: String) {
        _filterOptions.value = _filterOptions.value.copy(brand = brand)
    }

    fun updatePrice(range: ClosedFloatingPointRange<Float>) {
        _filterOptions.value = _filterOptions.value.copy(priceRange = range)
    }

    fun clearFilters() {
        _filterOptions.value = FilterOptions()
    }
}