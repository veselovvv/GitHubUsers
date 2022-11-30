package com.veselovvv.githubusers.presentation.users

import com.veselovvv.githubusers.TestResourceProvider
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.domain.users.UserDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseUsersDomainToUiMapperTest {
    private val userDomainToUiMapper = BaseUserDomainToUiMapper()
    private val resourceProvider = TestResourceProvider()

    private val mapper = BaseUsersDomainToUiMapper(resourceProvider, userDomainToUiMapper)

    @Test
    fun test_success() {
        val users = listOf(
            UserDomain(1, "first_login", "https://avatar1"),
            UserDomain(2, "second_login", "https://avatar2"),
            UserDomain(3, "third_login", "https://avatar3")
        )

        val expected = UsersUi.Success(users, userDomainToUiMapper)
        val actual = mapper.map(users)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = UsersUi.Fail(ErrorType.NO_CONNECTION, resourceProvider)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = UsersUi.Fail(ErrorType.SERVICE_UNAVAILABLE, resourceProvider)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }
}