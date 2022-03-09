package com.veselovvv.githubusers.data.users.net

import okhttp3.ResponseBody
import retrofit2.http.GET

interface UsersService {
    @GET("users")
    suspend fun fetchUsers(): ResponseBody
}