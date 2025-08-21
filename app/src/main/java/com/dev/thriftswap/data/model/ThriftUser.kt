package com.dev.thriftswap.data.model

data class ThriftUser(
    val userId: String,
    val displayName: String,
    val photoUrl: String,
    val id: String? = null
) {
    fun toMap(): Map<String, Any?> = mapOf(
        "userId" to userId,
        "displayName" to displayName,
        "photoUrl" to photoUrl,
        "id" to id
    )
}

