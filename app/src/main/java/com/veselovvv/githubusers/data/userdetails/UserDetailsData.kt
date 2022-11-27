package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.userdetails.UserDetailsDomain

data class UserDetailsData(
    private val name: String,
    private val email: String?,
    private val organization: String?,
    private val followingCount: Int,
    private val followersCount: Int,
    private val creationDate: String
) : Abstract.Object<UserDetailsDomain, UserDetailsDataToDomainMapper> {
    override fun map(mapper: UserDetailsDataToDomainMapper) =
        mapper.map(name, email, organization, followingCount, followersCount, creationDate)
}