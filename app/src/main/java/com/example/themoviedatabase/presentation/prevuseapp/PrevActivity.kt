package com.example.themoviedatabase.presentation.prevuseapp

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingActivity
import com.example.themoviedatabase.common.INT_DEFAULT
import com.example.themoviedatabase.common.extension.addToken
import com.example.themoviedatabase.common.extension.allow
import com.example.themoviedatabase.common.extension.deny
import com.example.themoviedatabase.common.gone
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.common.show
import com.example.themoviedatabase.databinding.PrevLoginActivityBinding
import com.example.themoviedatabase.presentation.home.HomeActivity
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState
import kotlinx.coroutines.launch

class PrevActivity : BaseBindingActivity<PrevLoginActivityBinding>(R.layout.prev_login_activity) {

    private val viewModel by viewModels<PrevViewModel>()

    override fun onInitView() {
        super.onInitView()

        binding.tvPrevLoginAskPermission.setOnSafeClick {

            binding.tvPrevLoginAskPermission.gone()
            binding.wvPrevLogin.show()

            setWebView()
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()

        coroutinesLaunch(viewModel.session) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        AppConfig.sessionResponse = it.data!!
                        viewModel.getAccount()
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.accountState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        AppConfig.account = it.data!!
                        viewModel.getMaxPopularity()
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.maxPopularity) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        lifecycleScope.launch {
                            val listPopularity = mutableListOf<Int>()
                            it.data?.forEach {
                                listPopularity.add(it.popularity?.toInt() ?: INT_DEFAULT)
                            }
                            AppConfig.maxPopularity = listPopularity.maxOrNull() ?: INT_DEFAULT
                        }
                        navigateTo(HomeActivity::class.java)
                    }
                }
            })
        }
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        binding.wvPrevLogin.apply {
            loadUrl(AppConfig.URL_PERMISSION.addToken(AppConfig.tokenResponse?.requestToken))
            settings.javaScriptEnabled = true

            webViewClient = object : WebViewClient() {
                override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                    if (request?.url.toString() == AppConfig.URL_PERMISSION.addToken(AppConfig.tokenResponse?.requestToken).allow()) {
                        AppConfig.tokenResponse?.let { token ->
                            viewModel.createSession(token)
                        }
                    }

                    if (request?.url.toString() == AppConfig.URL_PERMISSION.addToken(AppConfig.tokenResponse?.requestToken).deny()) {
                        Log.d(TAG, "shouldInterceptRequest: ko dc phep")
                    }

                    return super.shouldInterceptRequest(view, request)
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                }
            }
        }
    }
}
