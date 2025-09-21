package com.dev.thriftswap.data.datasources.local



import com.dev.thriftswap.R
import com.dev.thriftswap.data.model.Category
import com.dev.thriftswap.data.model.CategoryItem

class CategoryRepository {

    private val categories = listOf(
        Category(
            name = "T-Shirt",
            imageRes = R.drawable.ic_tshirt
        ),
        Category(
            name = "Gowns",
            imageRes = R.drawable.ic_gown
        ),
        Category(
            name = "Caps",
            imageRes = R.drawable.ic_cap
        ),
        Category(
            name = "Jacket",
            imageRes = R.drawable.ic_jacket
        ),
        Category(
            name = "Jeans",
            imageRes = R.drawable.ic_jean
        )
    )
    private val categoryItems = listOf(
        // Jeans items
        CategoryItem(
            categoryId = categories.find { it.name == "Jacket" }!!.categoryId,
            name = "Brown Jacket",
            imageRes = R.drawable.jacket_brown,
            price = 1000.0,
            grade = "A",
            size = "M"
        ),
        CategoryItem(
            categoryId = categories.find { it.name == "Jacket" }!!.categoryId,
            name = "Black Jacket",
            imageRes = R.drawable.jacket,
            price = 1000.0,
            grade = "A",
            size = "M"
        ),
        CategoryItem(
            categoryId = categories.find { it.name == "Jacket" }!!.categoryId,
            name = "Black Jacket",
            imageRes = R.drawable.jacket,
            price = 1000.0,
            grade = "A",
            size = "M"
        ),
        CategoryItem(
            categoryId = categories.find { it.name == "Jacket" }!!.categoryId,
            name = "Brown Jacket",
            imageRes = R.drawable.jacket_brown,
            price = 1000.0,
            grade = "A",
            size = "M"
        ),
        CategoryItem(
            categoryId = categories.find { it.name == "Jacket" }!!.categoryId,
            name = "Black Jacket",
            imageRes = R.drawable.jacket,
            price = 1000.0,
            grade = "A",
            size = "M"
        ),
        CategoryItem(
            categoryId = categories.find { it.name == "Jacket" }!!.categoryId,
            name = "Brown Jacket",
            imageRes = R.drawable.jacket_brown,
            price = 1000.0,
            grade = "A",
            size = "M"
        )
    )

    fun getCategories(): List<Category> = categories

    fun getCategory(id: String): Category? {
        return categories.find { it.categoryId == id }
    }

    fun getCategoryItems(categoryId: String): List<CategoryItem> {
        return categoryItems.filter { it.categoryId == categoryId }
    }
}
