package com.dev.thriftswap.domain.usecase.auth

import com.dev.thriftswap.domain.repository.AuthenticationRepository
import javax.inject.Inject

class LoginWithGoogleUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(idToken: String) =
        authenticationRepository.loginWithGoogle(idToken)

}