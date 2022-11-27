package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.data.users.net.UserCloud
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersCloudMapperTest {
    @Test
    fun test_mapping() {
        val mapper = UsersCloudMapper.Base(ToUserMapper.Base())
        val users = listOf(
            UserCloud(1, "first_login", "https://avatar1"),
            UserCloud(2, "second_login", "https://avatar2"),
            UserCloud(3, "third_login", "https://avatar3")
        )
        val expected = listOf(
            UserData(1, "first_login", "https://avatar1"),
            UserData(2, "second_login", "https://avatar2"),
            UserData(3, "third_login", "https://avatar3")
        )
        val actual = mapper.map(users)
        assertEquals(expected, actual)
    }
}