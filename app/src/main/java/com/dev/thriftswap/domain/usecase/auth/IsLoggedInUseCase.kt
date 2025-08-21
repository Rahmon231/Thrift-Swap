package com.dev.thriftswap.domain.usecase.auth

import com.dev.thriftswap.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    operator fun invoke() = flow { emit(authenticationRepository.isLoggedIn()) }
}