package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.users.UsersDomain
import java.lang.Exception

interface UsersDataToDomainMapper : Abstract.Mapper {
    fun map(users: List<UserData>): UsersDomain
    fun map(exception: Exception): UsersDomain
}