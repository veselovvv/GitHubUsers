package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.TestResourceProvider
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.data.users.UserData
import com.veselovvv.githubusers.presentation.users.BaseUserDomainToUiMapper
import com.veselovvv.githubusers.presentation.users.BaseUsersDomainToUiMapper
import com.veselovvv.githubusers.presentation.users.UsersUi
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class UsersDomainTest {
    @Test
    fun test_success() {
        val usersData = listOf(
            UserData(1, "first_login", "https://avatar1"),
            UserData(2, "second_login", "https://avatar2"),
            UserData(3, "third_login", "https://avatar3")
        )

        val usersDomain = listOf(
            UserDomain(1, "first_login", "https://avatar1"),
            UserDomain(2, "second_login", "https://avatar2"),
            UserDomain(3, "third_login", "https://avatar3")
        )

        val mapper = BaseUserDomainToUiMapper()
        val domain = UsersDomain.Success(usersData, BaseUserDataToDomainMapper())

        val expected = UsersUi.Success(usersDomain, mapper)
        val actual = domain.map(
            BaseUsersDomainToUiMapper(
                TestResourceProvider(),
                mapper
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val testResourceProvider = TestResourceProvider()

        var domain = UsersDomain.Fail(UnknownHostException())
        var expected = UsersUi.Fail(ErrorType.NO_CONNECTION, testResourceProvider)
        var actual = domain.map(
            BaseUsersDomainToUiMapper(
                testResourceProvider,
                BaseUserDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)

        domain = UsersDomain.Fail(Exception())
        expected = UsersUi.Fail(ErrorType.GENERIC_ERROR, testResourceProvider)
        actual = domain.map(
            BaseUsersDomainToUiMapper(
                testResourceProvider,
                BaseUserDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)
    }
}