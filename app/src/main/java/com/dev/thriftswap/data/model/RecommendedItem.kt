package com.dev.thriftswap.data.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class RecommendedItem(
    val id: String = UUID.randomUUID().toString(),  // auto-generated UUID
    val category: String,
    val name: String,
    val size: String,
    val price: Double,
    val grade: String,
    @DrawableRes val imageRes: Int
)

