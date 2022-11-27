package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.data.userdetails.net.UserDetailsCloud
import org.junit.Assert.assertEquals
import org.junit.Test

class UserDetailsCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = UserDetailsCloudMapper.Base(ToUserDetailsMapper.Base())

        val expected = UserDetailsData(
            "Alex",
            "alex@email.com",
            "org1",
            70,
            125,
            "27.03.2012"
        )

        val actual = mapper.map(
            UserDetailsCloud(
                "Alex",
                "alex@email.com",
                "org1",
                70,
                125,
                "27.03.2012"
            )
        )

        assertEquals(expected, actual)
    }
}