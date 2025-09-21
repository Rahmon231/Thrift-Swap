package com.dev.thriftswap.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.dev.thriftswap.data.datasources.local.CategoryRepository
import com.dev.thriftswap.data.datasources.local.RecommendedRepository
import com.dev.thriftswap.data.model.Category
import com.dev.thriftswap.data.model.CategoryItem
import com.dev.thriftswap.data.model.RecommendedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val recommendedRepository: RecommendedRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    private val _recommended = MutableStateFlow<List<RecommendedItem>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()
    val recommended: StateFlow<List<RecommendedItem>> = _recommended.asStateFlow()

    init {
        loadCategories()
        loadRecommended()
    }

    private fun loadRecommended(){
        _recommended.value = recommendedRepository.getRecommendedItems()
    }

    fun getRecommendedItem(id: String): RecommendedItem? {
        return recommended.value.find { it.id == id }
    }
    private fun loadCategories() {
        _categories.value = categoryRepository.getCategories()
    }

    fun getCategory(id: String): Category? {
        return categories.value.find { it.categoryId == id }
    }

    fun getCategoriesItemById(categoryId: String): List<CategoryItem> {
        return categoryRepository.getCategoryItems(categoryId)
    }
}
