package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.data.users.UserData
import com.veselovvv.githubusers.data.users.UserDataToDomainMapper
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.presentation.users.UsersUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

sealed class UsersDomain : Abstract.Object<UsersUi, UsersDomainToUiMapper> {
    class Success(
        private val users: List<UserData>,
        private val userMapper: UserDataToDomainMapper
    ) : UsersDomain() {
        override fun map(mapper: UsersDomainToUiMapper) = mapper.map(users.map { it.map(userMapper) })
    }

    class Fail(private val exception: Exception) : UsersDomain() {
        override fun map(mapper: UsersDomainToUiMapper) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
