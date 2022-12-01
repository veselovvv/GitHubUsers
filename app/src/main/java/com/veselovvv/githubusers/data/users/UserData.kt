package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.users.UserDomain

data class UserData(
    private val id: Int,
    private val login: String,
    private val avatarUrl: String
) : Abstract.Object<UserDomain, UserDataToDomainMapper> {
    override fun map(mapper: UserDataToDomainMapper) = mapper.map(id, login, avatarUrl)
}
