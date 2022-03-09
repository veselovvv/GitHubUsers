package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.presentation.users.UserUi

class UserDomain(
    private val id: Int, private val login: String, private val avatarUrl: String
) : Abstract.Object<UserUi, UserDomainToUiMapper> {
    override fun map(mapper: UserDomainToUiMapper) = mapper.map(id, login, avatarUrl)
}