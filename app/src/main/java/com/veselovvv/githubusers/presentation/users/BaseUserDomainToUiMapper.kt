package com.veselovvv.githubusers.presentation.users

import com.veselovvv.githubusers.domain.users.UserDomainToUiMapper

class BaseUserDomainToUiMapper : UserDomainToUiMapper {
    override fun map(id: Int, login: String, avatarUrl: String) = UserUi.Base(id, login, avatarUrl)
}