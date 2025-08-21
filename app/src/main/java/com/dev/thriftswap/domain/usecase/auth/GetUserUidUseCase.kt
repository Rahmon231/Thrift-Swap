package com.dev.thriftswap.domain.usecase.auth

import com.dev.thriftswap.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUidUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke() = flow { emit(authenticationRepository.userUid()) }
}