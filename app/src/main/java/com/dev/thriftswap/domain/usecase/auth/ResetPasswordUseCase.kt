package com.dev.thriftswap.domain.usecase.auth

import com.dev.thriftswap.domain.repository.AuthenticationRepository
import jakarta.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String) = authenticationRepository.resetPassword(email)
}