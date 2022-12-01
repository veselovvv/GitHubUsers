package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.data.TestException
import com.veselovvv.githubusers.data.userdetails.UserDetailsData
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseUsersDetailsDataToDomainMapperTest {
    private val userDetailsDataToDomainMapper = BaseUserDetailsDataToDomainMapper()
    private val mapper = BaseUsersDetailsDataToDomainMapper(userDetailsDataToDomainMapper)

    @Test
    fun test_success() {
        val userDetails = UserDetailsData(
            "Alex",
            "alex@email.com",
            "org1",
            70,
            125,
            "27.03.2012"
        )

        val expected = UsersDetailsDomain.Success(userDetails, userDetailsDataToDomainMapper)
        val actual = mapper.map(userDetails)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = UsersDetailsDomain.Fail(TestException("No connection"))
        var actual = mapper.map(TestException("No connection"))
        assertEquals(expected, actual)

        expected = UsersDetailsDomain.Fail(TestException("Generic error"))
        actual = mapper.map(TestException("Generic error"))
        assertEquals(expected, actual)
    }
}