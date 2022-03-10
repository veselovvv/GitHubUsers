package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.data.userdetails.UserDetailsDataToDomainMapper

class BaseUserDetailsDataToDomainMapper : UserDetailsDataToDomainMapper {
    override fun map(
        name: String,
        email: String?,
        organization: String?,
        followingCount: Int,
        followersCount: Int,
        creationDate: String
    ) = UserDetailsDomain(
        name, email, organization, followingCount, followersCount, creationDate
    )
}