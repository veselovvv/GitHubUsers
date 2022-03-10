package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.data.userdetails.UserDetailsData
import com.veselovvv.githubusers.data.userdetails.UserDetailsDataToDomainMapper
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.presentation.userdetails.UsersDetailsUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

sealed class UsersDetailsDomain : Abstract.Object<UsersDetailsUi, UsersDetailsDomainToUiMapper> {
    class Success(
        private val userDetails: UserDetailsData,
        private val userDetailsMapper: UserDetailsDataToDomainMapper
    ) : UsersDetailsDomain() {
        override fun map(mapper: UsersDetailsDomainToUiMapper) =
            mapper.map(userDetails.map(userDetailsMapper))
    }

    class Fail(private val exception: Exception) : UsersDetailsDomain() {
        override fun map(mapper: UsersDetailsDomainToUiMapper) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
