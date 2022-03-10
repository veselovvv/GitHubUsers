package com.veselovvv.githubusers.presentation.userdetails

import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomain
import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomainToUiMapper
import com.veselovvv.githubusers.domain.userdetails.UsersDetailsDomainToUiMapper
import com.veselovvv.githubusers.core.ResourceProvider

class BaseUsersDetailsDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val userDetailsMapper: UserDetailsDomainToUiMapper
) : UsersDetailsDomainToUiMapper {
    override fun map(userDetails: UserDetailsDomain) =
        UsersDetailsUi.Success(userDetails, userDetailsMapper)
    override fun map(errorType: ErrorType) = UsersDetailsUi.Fail(errorType, resourceProvider)
}