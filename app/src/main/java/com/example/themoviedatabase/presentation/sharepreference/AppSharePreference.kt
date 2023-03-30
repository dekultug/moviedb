package com.example.themoviedatabase.presentation.sharepreference

import android.content.Context
import android.content.SharedPreferences
import com.example.themoviedatabase.AppConfig

class AppSharedPreference(context: Context) {
    private val sharedPreference = context.getSharedPreferences(AppConfig.NAME_SHARED_PREFERENCE, Context.MODE_PRIVATE)
    private val sharedPreferenceEditor: SharedPreferences.Editor = sharedPreference.edit()

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferenceEditor.putBoolean(key, value)
        sharedPreferenceEditor.commit()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    fun putString(key: String, value: String) {
        sharedPreferenceEditor.putString(key, value)
        sharedPreferenceEditor.commit()
    }

    fun getString(key: String): String {
        return sharedPreference.getString(key, "").toString()
    }

}
