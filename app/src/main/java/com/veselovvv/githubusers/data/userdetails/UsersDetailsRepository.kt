package com.veselovvv.githubusers.data.userdetails

import java.lang.Exception

interface UsersDetailsRepository {
    suspend fun fetchUserDetails(login: String): UsersDetailsData

    class Base(
        private val cloudDataSource: UserDetailsCloudDataSource,
        private val userDetailsCloudMapper: UserDetailsCloudMapper
    ) : UsersDetailsRepository {
        override suspend fun fetchUserDetails(login: String) = try {
            val userDetailsCloud = cloudDataSource.fetchUserDetails(login)
            val userDetails = userDetailsCloudMapper.map(userDetailsCloud)
            UsersDetailsData.Success(userDetails)
        } catch (exception: Exception) {
            UsersDetailsData.Fail(exception)
        }
    }
}