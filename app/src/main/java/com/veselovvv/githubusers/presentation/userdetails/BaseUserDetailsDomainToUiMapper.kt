package com.veselovvv.githubusers.presentation.userdetails

import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomainToUiMapper

class BaseUserDetailsDomainToUiMapper : UserDetailsDomainToUiMapper {
    override fun map(
        name: String,
        email: String?,
        organization: String?,
        followingCount: Int,
        followersCount: Int,
        creationDate: String
    ) = UserDetailsUi.Base(
        name, email, organization, followingCount, followersCount, creationDate
    )
}