package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.data.TestException
import com.veselovvv.githubusers.data.userdetails.UserDetailsData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersDetailsInteractorTest {
    @Test
    fun test_success() = runBlocking {
        val userDetailsData = UserDetailsData(
            "Alex",
            "alex@email.com",
            "org1",
            70,
            125,
            "27.03.2012"
        )

        val userDetailsDataToDomainMapper = BaseUserDetailsDataToDomainMapper()

        val interactor = UsersDetailsInteractor.Base(
            TestUsersDetailsRepository(),
            BaseUsersDetailsDataToDomainMapper(userDetailsDataToDomainMapper)
        )

        val expected = UsersDetailsDomain.Success(userDetailsData, userDetailsDataToDomainMapper)
        val actual = interactor.fetchUserDetails("alex@email.com")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() = runBlocking {
        val userDetailsDataToDomainMapper = BaseUserDetailsDataToDomainMapper()

        val interactor = UsersDetailsInteractor.Base(
            TestUsersDetailsRepository(TestException("")),
            BaseUsersDetailsDataToDomainMapper(userDetailsDataToDomainMapper)
        )

        val expected = UsersDetailsDomain.Fail(TestException(""))
        val actual = interactor.fetchUserDetails("")
        assertEquals(expected, actual)
    }
}