package com.veselovvv.githubusers.presentation.users

import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.domain.users.UserDomain
import com.veselovvv.githubusers.domain.users.UserDomainToUiMapper
import com.veselovvv.githubusers.core.ResourceProvider

sealed class UsersUi : Abstract.Object<Unit, UsersCommunication> {
    data class Success(
        private val users: List<UserDomain>,
        private val userMapper: UserDomainToUiMapper
    ) : UsersUi() {
        override fun map(mapper: UsersCommunication) {
            val usersUi = users.map { it.map(userMapper) }
            mapper.map(usersUi)
        }
    }

    data class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : UsersUi() {
        override fun map(mapper: UsersCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }

            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(UserUi.Fail(message)))
        }
    }
}