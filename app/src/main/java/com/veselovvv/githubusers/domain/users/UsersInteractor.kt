package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.data.users.UsersDataToDomainMapper
import com.veselovvv.githubusers.data.users.UsersRepository

interface UsersInteractor {
    suspend fun fetchUsers(): UsersDomain
    suspend fun searchUsers(query: String): UsersDomain

    class Base(
        private val usersRepository: UsersRepository,
        private val mapper: UsersDataToDomainMapper
    ) : UsersInteractor {
        override suspend fun fetchUsers() = usersRepository.fetchUsers().map(mapper)
        override suspend fun searchUsers(query: String) = usersRepository.searchUsers(query).map(mapper)
    }
}