package com.veselovvv.githubusers.presentation.userdetails

import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomain
import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomainToUiMapper
import com.veselovvv.githubusers.core.ResourceProvider

sealed class UsersDetailsUi : Abstract.Object<Unit, UsersDetailsCommunication> {
    data class Success(
        private val userDetails: UserDetailsDomain,
        private val userDetailsMapper: UserDetailsDomainToUiMapper
    ) : UsersDetailsUi() {
        override fun map(mapper: UsersDetailsCommunication) {
            val userDetailsUi = userDetails.map(userDetailsMapper)
            mapper.map(userDetailsUi)
        }
    }

    data class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : UsersDetailsUi() {
        override fun map(mapper: UsersDetailsCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }

            val message = resourceProvider.getString(messageId)
            mapper.map(UserDetailsUi.Fail(message))
        }
    }
}