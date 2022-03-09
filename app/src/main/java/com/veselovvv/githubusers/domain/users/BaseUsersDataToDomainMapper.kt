package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.data.users.UserData
import com.veselovvv.githubusers.data.users.UserDataToDomainMapper
import com.veselovvv.githubusers.data.users.UsersDataToDomainMapper
import java.lang.Exception

class BaseUsersDataToDomainMapper(
    private val userMapper: UserDataToDomainMapper
) : UsersDataToDomainMapper {
    override fun map(users: List<UserData>) = UsersDomain.Success(users, userMapper)
    override fun map(exception: Exception) = UsersDomain.Fail(exception)
}