package com.dev.thriftswap.data.datasources.local

import com.dev.thriftswap.R
import com.dev.thriftswap.data.model.RecommendedItem

class RecommendedRepository {

    private val recommendedItems = listOf(
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Gown",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_gown
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Top",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_top
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Halter",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_halter
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Boho Gown",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.boho_gown
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Top",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_top
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Halter",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_halter
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Boho Gown",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.boho_gown
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Top",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_top
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Halter",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_halter
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Boho Gown",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.boho_gown
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Top",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_top
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Floral Halter",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.floral_halter
        ),
        RecommendedItem(
            category = "Women Dress",
            name = "Boho Gown",
            size = "M",
            price = 2000.0,
            grade = "Grade 1",
            imageRes = R.drawable.boho_gown
        )
    )

    fun getRecommendedItems(): List<RecommendedItem> = recommendedItems

    fun getRecommendedItem(id: String): RecommendedItem? {
        return recommendedItems.find { it.id == id }
    }
}