package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.users.UserDomain

interface UserDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, login: String, avatarUrl: String): UserDomain
}