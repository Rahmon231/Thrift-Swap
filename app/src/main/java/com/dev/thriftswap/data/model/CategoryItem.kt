package com.dev.thriftswap.data.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class CategoryItem(
    val id: String = UUID.randomUUID().toString(),  // auto-generated UUID
    val categoryId: String,
    val name: String,
    val size: String,
    val price: Double,
    val grade: String,
    @DrawableRes val imageRes: Int
)
