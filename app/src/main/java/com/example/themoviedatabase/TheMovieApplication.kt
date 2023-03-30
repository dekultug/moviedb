package com.example.themoviedatabase

import android.app.Application
import com.example.themoviedatabase.common.setApplication
import com.example.themoviedatabase.presentation.sharepreference.DoSharedPreference

class TheMovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setApplication(this)
        DoSharedPreference.init(applicationContext)
    }
}
