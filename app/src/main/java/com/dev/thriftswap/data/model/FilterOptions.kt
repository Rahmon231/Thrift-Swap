package com.dev.thriftswap.data.model

data class FilterOptions(
    val gender: String = "Men",
    val size: String = "L",
    val color: String = "White",
    val brand: String = "M&S",
    val priceRange: ClosedFloatingPointRange<Float> = 0f..1000f
)

