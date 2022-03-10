package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.data.userdetails.UserDetailsData
import com.veselovvv.githubusers.data.userdetails.UserDetailsDataToDomainMapper
import com.veselovvv.githubusers.data.userdetails.UsersDetailsDataToDomainMapper
import java.lang.Exception

class BaseUsersDetailsDataToDomainMapper(
    private val userDetailsMapper: UserDetailsDataToDomainMapper
) : UsersDetailsDataToDomainMapper {
    override fun map(userDetails: UserDetailsData) =
        UsersDetailsDomain.Success(userDetails, userDetailsMapper)
    override fun map(exception: Exception) = UsersDetailsDomain.Fail(exception)
}