package com.example.themoviedatabase.presentation.splash

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.di.RepoFactory
import com.example.themoviedatabase.domain.model.authen.Session
import com.example.themoviedatabase.domain.model.authen.Token
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
    private val _tokenState = MutableStateFlow(FlowResult.newInstance<Token>())
    val token = _tokenState.asStateFlow()

    private val _sessionState = MutableStateFlow(FlowResult.newInstance<Session>())
    val session = _sessionState.asStateFlow()

    private val repo = RepoFactory.getAuthImpl()

    init {

    }

    fun getToken() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createToken()
                .onStart {
                    _tokenState.loading()
                }
                .onException {
                    _tokenState.failure(it)
                }
                .collect {
                    if (it != null) {
                        _tokenState.success(it)
                    }
                }
        }
    }

    fun createSession(token: Token) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createSession(token)
                .onStart {
                    _sessionState.loading()
                }
                .onException {
                    _sessionState.failure(it)
                }
                .collect {
                    if (it != null) {
                        _sessionState.success(it)
                    }
                }
        }
    }
}
