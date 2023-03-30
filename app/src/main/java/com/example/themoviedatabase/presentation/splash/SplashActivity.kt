package com.example.themoviedatabase.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.lifecycleScope
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.R
import com.example.themoviedatabase.common.component.BaseBindingActivity
import com.example.themoviedatabase.common.extension.addToken
import com.example.themoviedatabase.databinding.SplashActvityBinding
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseBindingActivity<SplashActvityBinding>(R.layout.splash_actvity) {

    private val viewModel by lazy { SplashViewModel() }

    override fun onInitView() {
        super.onInitView()
        lifecycleScope.launch {
            delay(1000)
            viewModel.getToken()
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()

        coroutinesLaunch(viewModel.token) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        AppConfig.token = it.data!!
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(AppConfig.URL_PERMISSION.addToken(it.data!!.requestToken))
                        startActivity(intent)
                    }
                }
            })
        }

    }
}
