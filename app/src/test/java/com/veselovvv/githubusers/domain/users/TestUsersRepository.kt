package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.data.users.UserData
import com.veselovvv.githubusers.data.users.UsersData
import com.veselovvv.githubusers.data.users.UsersRepository

class TestUsersRepository(
    private val exception: Exception? = null
) : UsersRepository {
    private val users = listOf(
        UserData(1, "first_login", "https://avatar1"),
        UserData(2, "second_login", "https://avatar2"),
        UserData(3, "third_login", "https://avatar3")
    )

    override suspend fun fetchUsers() =
        if (exception == null) UsersData.Success(users)
        else UsersData.Fail(exception)

    override suspend fun searchUsers(query: String) =
        if (exception == null) {
            val users = when (query) {
                "first" -> listOf(
                    UserData(1, "first_login", "https://avatar1")
                )
                "second" -> listOf(
                    UserData(2, "second_login", "https://avatar2")
                )
                else -> listOf(
                    UserData(3, "third_login", "https://avatar3")
                )
            }
            UsersData.Success(users)
        } else
            UsersData.Fail(exception)
}