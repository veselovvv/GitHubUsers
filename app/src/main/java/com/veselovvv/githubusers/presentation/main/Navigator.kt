package com.veselovvv.githubusers.presentation.main

import android.content.Context
import com.veselovvv.githubusers.core.Read
import com.veselovvv.githubusers.core.Save

interface Navigator : Save<Int>, Read<Int> {
    class Base(context: Context) : Navigator {
        private val sharedPreferences =
            context.getSharedPreferences(NAVIGATOR_FILE_NAME, Context.MODE_PRIVATE)

        override fun save(data: Int) = sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()
        override fun read(): Int = sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)

        companion object {
            private const val NAVIGATOR_FILE_NAME = "navigation"
            private const val CURRENT_SCREEN_KEY = "screenId"
        }
    }
}

class Screens {
    companion object {
        const val USERS_SCREEN = 0
        const val USER_DETAILS_SCREEN = 1
    }
}