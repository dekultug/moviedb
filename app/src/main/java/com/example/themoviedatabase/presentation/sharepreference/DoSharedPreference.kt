package com.example.themoviedatabase.presentation.sharepreference

import android.content.Context
import com.example.themoviedatabase.common.BOOLEAN_DEFAULT
import com.example.themoviedatabase.common.STRING_DEFAULT
import com.google.gson.Gson

class DoSharedPreference {
    private var mySharedPreference: AppSharedPreference? = null

    companion object {
        fun init(context: Context) {
            instance = DoSharedPreference()
            instance!!.mySharedPreference = AppSharedPreference(context)
        }

        private var instance: DoSharedPreference? = null

        @JvmName("getInstance1")
        fun getInstance(): DoSharedPreference {
            if (instance == null) {
                instance = DoSharedPreference()
            }
            return instance as DoSharedPreference
        }

        fun putBoolean(key: String, value: Boolean) =
            getInstance().mySharedPreference?.putBoolean(key, value)

        fun getBoolean(key: String): Boolean =
            getInstance().mySharedPreference?.getBoolean(key) ?: BOOLEAN_DEFAULT

        fun putString(key: String, value: String) =
            getInstance().mySharedPreference?.putString(key, value)

        fun getString(key: String): String =
            getInstance().mySharedPreference?.getString(key) ?: STRING_DEFAULT

        fun putObject(key: String, value: Any) {
            val gson = Gson()
            val convertObjectToString = gson.toJson(value)
            getInstance().mySharedPreference!!.putString(key, convertObjectToString)
        }

        fun getObject(key: String): Any {
            val gson = Gson()
            val strObject = gson.toJsonTree(getInstance().mySharedPreference!!.getString(key))
            return gson.fromJson(strObject, Any::class.java)
        }
    }
}
