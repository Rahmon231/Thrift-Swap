package com.dev.thriftswap.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.dev.thriftswap.data.datasources.local.CategoryRepository
import com.dev.thriftswap.data.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        _categories.value = categoryRepository.getCategories()
    }

    fun getCategory(id: String): Category? {
        return categories.value.find { it.categoryId == id }
    }
}
