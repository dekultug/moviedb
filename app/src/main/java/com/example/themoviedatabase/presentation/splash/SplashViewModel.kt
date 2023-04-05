package com.example.themoviedatabase.presentation.splash

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.factory.RepoFactory
import com.example.themoviedatabase.domain.model.authen.SessionResponse
import com.example.themoviedatabase.domain.model.authen.TokenResponse
import failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import loading
import onException
import success

class SplashViewModel : BaseViewModel() {
    private val _tokenResponseState = MutableStateFlow(FlowResult.newInstance<TokenResponse>())
    val token = _tokenResponseState.asStateFlow()

    private val _sessionResponseState = MutableStateFlow(FlowResult.newInstance<SessionResponse>())
    val session = _sessionResponseState.asStateFlow()

    private val repo = RepoFactory.getAuthImpl()

    init {

    }

    fun getToken() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createToken()
                .onStart {
                    _tokenResponseState.loading()
                }
                .onException {
                    _tokenResponseState.failure(it)
                }
                .collect {
                    if (it != null) {
                        _tokenResponseState.success(it)
                    }
                }
        }
    }

    fun createSession(tokenResponse: TokenResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createSession(tokenResponse)
                .onStart {
                    _sessionResponseState.loading()
                }
                .onException {
                    _sessionResponseState.failure(it)
                }
                .collect {
                    if (it != null) {
                        _sessionResponseState.success(it)
                    }
                }
        }
    }
}
