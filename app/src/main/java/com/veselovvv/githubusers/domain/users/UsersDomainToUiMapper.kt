package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.presentation.users.UsersUi

interface UsersDomainToUiMapper : Abstract.Mapper {
    fun map(users: List<UserDomain>): UsersUi
    fun map(errorType: ErrorType): UsersUi
}