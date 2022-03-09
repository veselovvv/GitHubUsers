package com.veselovvv.githubusers.presentation.users

import android.content.Context
import com.veselovvv.githubusers.core.Read
import com.veselovvv.githubusers.core.Save

interface UserCache : Save<Pair<String, String>>, Read<Pair<String, String>> {
    class Base(context: Context) : UserCache {
        private val sharedPreferences =
            context.getSharedPreferences(USER_LOGIN_FILENAME, Context.MODE_PRIVATE)

        override fun read() = Pair(
            sharedPreferences.getString(USER_LOGIN_KEY, "") ?: "",
            sharedPreferences.getString(USER_AVATAR_URL_KEY, "") ?: ""
        )

        override fun save(data: Pair<String, String>) =
            sharedPreferences.edit()
                .putString(USER_LOGIN_KEY, data.first)
                .putString(USER_AVATAR_URL_KEY, data.second)
                .apply()

        private companion object {
            const val USER_LOGIN_FILENAME = "userLogin"
            const val USER_LOGIN_KEY = "userLoginKey"
            const val USER_AVATAR_URL_KEY = "userAvatarUrlKey"
        }
    }
}