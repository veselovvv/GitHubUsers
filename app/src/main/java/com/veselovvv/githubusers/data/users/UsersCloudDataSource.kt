package com.veselovvv.githubusers.data.users

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.veselovvv.githubusers.data.users.net.UserCloud
import com.veselovvv.githubusers.data.users.net.UsersService

interface UsersCloudDataSource {
    suspend fun fetchUsers(): List<UserCloud>

    class Base(private val service: UsersService, private val gson: Gson) : UsersCloudDataSource {
        private val type = object : TypeToken<List<UserCloud>>() {}.type

        override suspend fun fetchUsers(): List<UserCloud> =
            gson.fromJson(service.fetchUsers().string(), type)
    }
}