package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.presentation.users.UserUi

interface UserDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, login: String, avatarUrl: String): UserUi
}