package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.TestResourceProvider
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.data.userdetails.UserDetailsData
import com.veselovvv.githubusers.presentation.userdetails.BaseUserDetailsDomainToUiMapper
import com.veselovvv.githubusers.presentation.userdetails.BaseUsersDetailsDomainToUiMapper
import com.veselovvv.githubusers.presentation.userdetails.UsersDetailsUi
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

class UsersDetailsDomainTest {
    @Test
    fun test_success() {
        val userDetailsData = UserDetailsData(
            "Alex",
            "alex@email.com",
            "org1",
            70,
            125,
            "27.03.2012"
        )

        val userDetailsDomain = UserDetailsDomain(
            "Alex",
            "alex@email.com",
            "org1",
            70,
            125,
            "27.03.2012"
        )

        val mapper = BaseUserDetailsDomainToUiMapper()
        val domain = UsersDetailsDomain.Success(userDetailsData, BaseUserDetailsDataToDomainMapper())

        val expected = UsersDetailsUi.Success(userDetailsDomain, mapper)
        val actual = domain.map(
            BaseUsersDetailsDomainToUiMapper(
                TestResourceProvider(),
                mapper
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val testResourceProvider = TestResourceProvider()

        var domain = UsersDetailsDomain.Fail(UnknownHostException())
        var expected = UsersDetailsUi.Fail(ErrorType.NO_CONNECTION, testResourceProvider)
        var actual = domain.map(
            BaseUsersDetailsDomainToUiMapper(
                testResourceProvider,
                BaseUserDetailsDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)

        domain = UsersDetailsDomain.Fail(Exception())
        expected = UsersDetailsUi.Fail(ErrorType.GENERIC_ERROR, testResourceProvider)
        actual = domain.map(
            BaseUsersDetailsDomainToUiMapper(
                testResourceProvider,
                BaseUserDetailsDomainToUiMapper()
            )
        )
        assertEquals(expected, actual)
    }
}