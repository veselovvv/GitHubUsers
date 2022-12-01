package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.data.TestException
import com.veselovvv.githubusers.data.users.UserData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersInteractorTest {
    @Test
    fun test_fetch_users_success() = runBlocking {
        val users = listOf(
            UserData(1, "first_login", "https://avatar1"),
            UserData(2, "second_login", "https://avatar2"),
            UserData(3, "third_login", "https://avatar3")
        )

        val userDataToDomainMapper = BaseUserDataToDomainMapper()

        val interactor = UsersInteractor.Base(
            TestUsersRepository(),
            BaseUsersDataToDomainMapper(userDataToDomainMapper)
        )

        val expected = UsersDomain.Success(users, userDataToDomainMapper)
        val actual = interactor.fetchUsers()
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_users_fail() = runBlocking {
        val userDataToDomainMapper = BaseUserDataToDomainMapper()

        val interactor = UsersInteractor.Base(
            TestUsersRepository(TestException("")),
            BaseUsersDataToDomainMapper(userDataToDomainMapper)
        )

        val expected = UsersDomain.Fail(TestException(""))
        val actual = interactor.fetchUsers()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_users_success() = runBlocking {
        val userDataToDomainMapper = BaseUserDataToDomainMapper()

        val interactor = UsersInteractor.Base(
            TestUsersRepository(),
            BaseUsersDataToDomainMapper(userDataToDomainMapper)
        )

        val expected = UsersDomain.Success(
            listOf(
                UserData(2, "second_login", "https://avatar2")
            ),
            userDataToDomainMapper)
        val actual = interactor.searchUsers("second")
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_users_fail() = runBlocking {
        val userDataToDomainMapper = BaseUserDataToDomainMapper()

        val interactor = UsersInteractor.Base(
            TestUsersRepository(TestException("")),
            BaseUsersDataToDomainMapper(userDataToDomainMapper)
        )

        val expected = UsersDomain.Fail(TestException(""))
        val actual = interactor.searchUsers("")
        assertEquals(expected, actual)
    }
}
