package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.userdetails.UsersDetailsDomain
import java.lang.Exception

sealed class UsersDetailsData : Abstract.Object<UsersDetailsDomain, UsersDetailsDataToDomainMapper> {
    data class Success(private val userDetails: UserDetailsData) : UsersDetailsData() {
        override fun map(mapper: UsersDetailsDataToDomainMapper) = mapper.map(userDetails)
    }

    data class Fail(private val exception: Exception) : UsersDetailsData() {
        override fun map(mapper: UsersDetailsDataToDomainMapper) = mapper.map(exception)
    }
}