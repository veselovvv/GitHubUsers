package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.data.TestException
import com.veselovvv.githubusers.data.users.net.UserCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseUsersRepositoryTest {
    @Test
    fun test_fetch_users_success() = runBlocking {
        val testCloudDataSource = TestUsersCloudDataSource(true)
        val repository = UsersRepository.Base(
            testCloudDataSource,
            UsersCloudMapper.Base(ToUserMapper.Base())
        )

        val expected = UsersData.Success(
            listOf(
                UserData(1, "first_login", "https://avatar1"),
                UserData(2, "second_login", "https://avatar2"),
                UserData(3, "third_login", "https://avatar3")
            )
        )

        val actual = repository.fetchUsers()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_users_fail() = runBlocking {
        val testCloudDataSource = TestUsersCloudDataSource(false)
        val repository = UsersRepository.Base(
            testCloudDataSource,
            UsersCloudMapper.Base(ToUserMapper.Base())
        )

        val expected = UsersData.Fail(TestException(""))
        val actual = repository.fetchUsers()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_users_success() = runBlocking {
        val testCloudDataSource = TestUsersCloudDataSource(true)
        val repository = UsersRepository.Base(
            testCloudDataSource,
            UsersCloudMapper.Base(ToUserMapper.Base())
        )

        val expected = UsersData.Success(
            listOf(
                UserData(1, "first_login", "https://avatar1")
            )
        )

        val actual = repository.searchUsers("first")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_users_fail() = runBlocking {
        val testCloudDataSource = TestUsersCloudDataSource(false)
        val repository = UsersRepository.Base(
            testCloudDataSource,
            UsersCloudMapper.Base(ToUserMapper.Base())
        )

        val expected = UsersData.Fail(TestException(""))
        val actual = repository.searchUsers("")
        assertEquals(expected, actual)
    }

    class TestUsersCloudDataSource(private val success: Boolean) : UsersCloudDataSource {
        override suspend fun fetchUsers() = if (success) listOf(
            UserCloud(1, "first_login", "https://avatar1"),
            UserCloud(2, "second_login", "https://avatar2"),
            UserCloud(3, "third_login", "https://avatar3")
        ) else throw TestException("")
    }
}