package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.data.users.UserDataToDomainMapper

class BaseUserDataToDomainMapper : UserDataToDomainMapper {
    override fun map(id: Int, login: String, avatarUrl: String) = UserDomain(id, login, avatarUrl)
}