package com.dev.thriftswap.presentation.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.thriftswap.domain.repository.AuthenticationRepository
import com.dev.thriftswap.utils.Response
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardScreenViewModel @Inject constructor(
    private val repo: AuthenticationRepository
) : ViewModel() {

    private val _googleLoginState = MutableStateFlow<Response<AuthResult>>(Response.Idle)
    val googleLoginState: StateFlow<Response<AuthResult>> = _googleLoginState

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            repo.loginWithGoogle(idToken).collectLatest { response ->
                _googleLoginState.value = response
            }
        }
    }
    fun resetState() {
        _googleLoginState.value = Response.Idle
    }
}