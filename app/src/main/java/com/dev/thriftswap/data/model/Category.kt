package com.dev.thriftswap.data.model

import java.util.UUID

data class Category(
    val categoryId: String = UUID.randomUUID().toString(),
    val name: String,     // e.g. "T-Shirt"
    val imageUrl: String? = null,   // remote URL for category image
    val imageRes: Int? = null       // local drawable resource ID
)
