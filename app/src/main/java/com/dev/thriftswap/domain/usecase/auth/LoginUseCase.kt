package com.dev.thriftswap.domain.usecase.auth

import com.dev.thriftswap.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authenticationRepository.login(email, password)
}