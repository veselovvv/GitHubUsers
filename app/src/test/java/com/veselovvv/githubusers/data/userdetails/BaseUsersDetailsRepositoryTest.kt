package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.data.TestException
import com.veselovvv.githubusers.data.userdetails.net.UserDetailsCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseUsersDetailsRepositoryTest {
    @Test
    fun test_fetch_user_details_success() = runBlocking {
        val testCloudDataSource = TestUserDetailsCloudDataSource(true)
        val repository = UsersDetailsRepository.Base(
            testCloudDataSource,
            UserDetailsCloudMapper.Base(ToUserDetailsMapper.Base())
        )

        val expected = UsersDetailsData.Success(
            UserDetailsData(
                "Alex",
                "alex@email.com",
                "org1",
                70,
                125,
                "27.03.2012"
            )
        )
        val actual = repository.fetchUserDetails("first_login")
        assertEquals(expected, actual)
    }

    @Test
    fun test_fetch_user_details_fail() = runBlocking {
        val testCloudDataSource = TestUserDetailsCloudDataSource(false)
        val repository = UsersDetailsRepository.Base(
            testCloudDataSource,
            UserDetailsCloudMapper.Base(ToUserDetailsMapper.Base())
        )

        val expected = UsersDetailsData.Fail(TestException(""))
        val actual = repository.fetchUserDetails("")
        assertEquals(expected, actual)
    }

    class TestUserDetailsCloudDataSource(private val success: Boolean) : UserDetailsCloudDataSource {
        override suspend fun fetchUserDetails(login: String) = if (success) {
            UserDetailsCloud(
                "Alex",
                "alex@email.com",
                "org1",
                70,
                125,
                "27.03.2012"
            )
        } else throw TestException("")
    }
}