package com.veselovvv.githubusers.domain.users

import com.veselovvv.githubusers.data.TestException
import com.veselovvv.githubusers.data.users.UserData
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseUsersDataToDomainMapperTest {
    private val userDataToDomainMapper = BaseUserDataToDomainMapper()
    private val mapper = BaseUsersDataToDomainMapper(userDataToDomainMapper)

    @Test
    fun test_success() {
        val users = listOf(
            UserData(1, "first_login", "https://avatar1"),
            UserData(2, "second_login", "https://avatar2"),
            UserData(3, "third_login", "https://avatar3")
        )

        val expected = UsersDomain.Success(users, userDataToDomainMapper)
        val actual = mapper.map(users)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = UsersDomain.Fail(TestException("No connection"))
        var actual = mapper.map(TestException("No connection"))
        assertEquals(expected, actual)

        expected = UsersDomain.Fail(TestException("Generic error"))
        actual = mapper.map(TestException("Generic error"))
        assertEquals(expected, actual)
    }
}