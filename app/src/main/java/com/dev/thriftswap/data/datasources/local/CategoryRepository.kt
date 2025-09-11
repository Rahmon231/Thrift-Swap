package com.dev.thriftswap.data.datasources.local



import com.dev.thriftswap.R
import com.dev.thriftswap.data.model.Category

class CategoryRepository {

    fun getCategories(): List<Category> {
        return listOf(
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
            ),
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
    }
}
