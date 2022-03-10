package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomain

interface UserDetailsDataToDomainMapper : Abstract.Mapper {
    fun map(
        name: String,
        email: String?,
        organization: String?,
        followingCount: Int,
        followersCount: Int,
        creationDate: String
    ): UserDetailsDomain
}