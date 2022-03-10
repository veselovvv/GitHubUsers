package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.core.Abstract

interface ToUserDetailsMapper : Abstract.Mapper {
    fun map(
        name: String,
        email: String?,
        organization: String?,
        followingCount: Int,
        followersCount: Int,
        creationDate: String
    ): UserDetailsData

    class Base : ToUserDetailsMapper {
        override fun map(
            name: String,
            email: String?,
            organization: String?,
            followingCount: Int,
            followersCount: Int,
            creationDate: String
        ) = UserDetailsData(
            name, email, organization, followingCount, followersCount, creationDate
        )
    }
}