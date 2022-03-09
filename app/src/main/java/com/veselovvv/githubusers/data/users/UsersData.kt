package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.users.UsersDomain
import java.lang.Exception

sealed class UsersData : Abstract.Object<UsersDomain, UsersDataToDomainMapper> {
    data class Success(private val users: List<UserData>) : UsersData() {
        override fun map(mapper: UsersDataToDomainMapper) = mapper.map(users)
    }

    data class Fail(private val exception: Exception) : UsersData() {
        override fun map(mapper: UsersDataToDomainMapper) = mapper.map(exception)
    }
}
