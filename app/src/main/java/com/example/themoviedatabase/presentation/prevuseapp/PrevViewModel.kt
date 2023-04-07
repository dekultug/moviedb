package com.example.themoviedatabase.presentation.prevuseapp

import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.BaseViewModel
import com.example.themoviedatabase.common.getAppString
import com.example.themoviedatabase.common.thread.FlowResult
import com.example.themoviedatabase.domain.model.accout.detail.AccountResponse
import com.example.themoviedatabase.domain.model.authen.SessionResponse
import com.example.themoviedatabase.domain.model.authen.TokenResponse
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.factory.RepoFactory
import failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import loading
import onException
import success

class PrevViewModel : BaseViewModel() {

    private var _sessionResponseState = MutableStateFlow(FlowResult.newInstance<SessionResponse>())
    val session = _sessionResponseState.asStateFlow()

    private var _accountState = MutableStateFlow(FlowResult.newInstance<AccountResponse>())
    val accountState = _accountState.asStateFlow()

    private var _maxPopularity = MutableStateFlow(FlowResult.newInstance<List<TrendingResponse>>())
    val maxPopularity = _maxPopularity.asStateFlow()

    private val repoAuth = RepoFactory.getAuthImpl()
    private val repoAccount = RepoFactory.getAccountImpl()
    private val repoTrending = RepoFactory.getTrendingImpl()

    init {

    }

    fun createSession(tokenResponse: TokenResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            repoAuth.createSession(tokenResponse)
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

    fun getAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            repoAccount.getAccount()
                .onStart {
                    _accountState.loading()
                }
                .onException {
                    _accountState.failure(it)
                }
                .collect {
                    if (it != null) {
                        _accountState.success(it)
                    }
                }
        }
    }

    fun getMaxPopularity() {
        viewModelScope.launch(Dispatchers.IO) {
            repoTrending.getTrending(getAppString(R.string.week), getAppString(R.string._personal))
                .onStart {
                    _maxPopularity.loading()
                }
                .onException {
                    _maxPopularity.failure(it)
                }
                .collect {
                    _maxPopularity.success(it?: listOf())
                }
        }
    }
}
