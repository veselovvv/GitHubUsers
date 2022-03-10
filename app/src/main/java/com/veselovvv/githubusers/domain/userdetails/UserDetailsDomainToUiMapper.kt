package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.presentation.userdetails.UserDetailsUi

interface UserDetailsDomainToUiMapper : Abstract.Mapper {
    fun map(
        name: String,
        email: String?,
        organization: String?,
        followingCount: Int,
        followersCount: Int,
        creationDate: String
    ): UserDetailsUi
}