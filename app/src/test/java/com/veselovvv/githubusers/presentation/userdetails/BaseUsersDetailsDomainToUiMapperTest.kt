package com.veselovvv.githubusers.presentation.userdetails

import com.veselovvv.githubusers.TestResourceProvider
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomain
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseUsersDetailsDomainToUiMapperTest {
    private val userDetailsDomainToUiMapper = BaseUserDetailsDomainToUiMapper()
    private val resourceProvider = TestResourceProvider()

    private val mapper = BaseUsersDetailsDomainToUiMapper(
        resourceProvider,
        userDetailsDomainToUiMapper
    )

    @Test
    fun test_success() {
        val userDetails = UserDetailsDomain(
            "Alex",
            "alex@email.com",
            "org1",
            70,
            125,
            "27.03.2012"
        )

        val expected = UsersDetailsUi.Success(userDetails, userDetailsDomainToUiMapper)
        val actual = mapper.map(userDetails)
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        var expected = UsersDetailsUi.Fail(ErrorType.NO_CONNECTION, resourceProvider)
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        expected = UsersDetailsUi.Fail(ErrorType.SERVICE_UNAVAILABLE, resourceProvider)
        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }
}