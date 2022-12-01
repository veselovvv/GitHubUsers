package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.data.userdetails.UserDetailsData
import com.veselovvv.githubusers.data.userdetails.UsersDetailsData
import com.veselovvv.githubusers.data.userdetails.UsersDetailsRepository

class TestUsersDetailsRepository(
    private val exception: Exception? = null
) : UsersDetailsRepository {
    private val userDetailsData = UserDetailsData(
        "Alex",
        "alex@email.com",
        "org1",
        70,
        125,
        "27.03.2012"
    )

    override suspend fun fetchUserDetails(login: String) =
        if (exception == null) UsersDetailsData.Success(userDetailsData)
        else UsersDetailsData.Fail(exception)
}