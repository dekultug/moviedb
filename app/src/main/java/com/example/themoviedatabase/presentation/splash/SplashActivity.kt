package com.example.themoviedatabase.presentation.splash

import android.annotation.SuppressLint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingActivity
import com.example.themoviedatabase.databinding.SplashActvityBinding
import com.example.themoviedatabase.presentation.prevuseapp.PrevActivity
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseBindingActivity<SplashActvityBinding>(R.layout.splash_actvity) {

    private val viewModel by viewModels<SplashViewModel>()

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
                        AppConfig.tokenResponse = it.data!!
                        navigateTo(PrevActivity::class.java)
                    }
                }
            })
        }
    }
}
