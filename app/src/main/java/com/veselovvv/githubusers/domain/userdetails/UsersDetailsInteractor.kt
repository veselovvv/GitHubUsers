package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.data.userdetails.UsersDetailsDataToDomainMapper
import com.veselovvv.githubusers.data.userdetails.UsersDetailsRepository

interface UsersDetailsInteractor {
    suspend fun fetchUserDetails(login: String): UsersDetailsDomain

    class Base(
        private val usersDetailsRepository: UsersDetailsRepository,
        private val mapper: UsersDetailsDataToDomainMapper
    ) : UsersDetailsInteractor {
        override suspend fun fetchUserDetails(login: String) =
            usersDetailsRepository.fetchUserDetails(login).map(mapper)
    }
}