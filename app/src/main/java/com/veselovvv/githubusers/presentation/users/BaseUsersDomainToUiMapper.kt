package com.veselovvv.githubusers.presentation.users

import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.domain.users.UserDomain
import com.veselovvv.githubusers.domain.users.UserDomainToUiMapper
import com.veselovvv.githubusers.domain.users.UsersDomainToUiMapper
import com.veselovvv.githubusers.core.ResourceProvider

class BaseUsersDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val userMapper: UserDomainToUiMapper
) : UsersDomainToUiMapper {
    override fun map(users: List<UserDomain>) = UsersUi.Success(users, userMapper)
    override fun map(errorType: ErrorType) = UsersUi.Fail(errorType, resourceProvider)
}