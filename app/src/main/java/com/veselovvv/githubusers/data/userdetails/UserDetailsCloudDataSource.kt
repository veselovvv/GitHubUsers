package com.veselovvv.githubusers.data.userdetails

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.veselovvv.githubusers.data.userdetails.net.UserDetailsCloud
import com.veselovvv.githubusers.data.userdetails.net.UserDetailsService

interface UserDetailsCloudDataSource {
    suspend fun fetchUserDetails(login: String): UserDetailsCloud

    class Base(
        private val service: UserDetailsService, private val gson: Gson
    ) : UserDetailsCloudDataSource {
        private val type = object : TypeToken<UserDetailsCloud>() {}.type

        override suspend fun fetchUserDetails(login: String): UserDetailsCloud =
            gson.fromJson(service.fetchUserDetails(login).string(), type)
    }
}