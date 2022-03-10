package com.veselovvv.githubusers.data.userdetails.net

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsService {
    @GET("users/{login}")
    suspend fun fetchUserDetails(@Path("login") login: String): ResponseBody
}