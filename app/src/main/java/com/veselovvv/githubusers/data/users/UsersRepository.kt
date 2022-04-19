package com.veselovvv.githubusers.data.users

import java.lang.Exception

interface UsersRepository {
    suspend fun fetchUsers(): UsersData
    suspend fun searchUsers(query: String): UsersData

    class Base(
        private val cloudDataSource: UsersCloudDataSource,
        private val usersCloudMapper: UsersCloudMapper
    ) : UsersRepository {
        override suspend fun fetchUsers() = try {
            val usersCloudList = cloudDataSource.fetchUsers()
            val users = usersCloudMapper.map(usersCloudList)
            UsersData.Success(users)
        } catch (exception: Exception) {
            UsersData.Fail(exception)
        }

        override suspend fun searchUsers(query: String) = try {
            val usersCloudList = cloudDataSource.fetchUsers().filter {
                userCloud -> userCloud.userLogin.startsWith(query)
            }
            val users = usersCloudMapper.map(usersCloudList)
            UsersData.Success(users)
        } catch (exception: Exception) {
            UsersData.Fail(exception)
        }
    }
}