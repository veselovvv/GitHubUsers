package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.presentation.userdetails.UserDetailsUi

class UserDetailsDomain(
    private val name: String,
    private val email: String?,
    private val organization: String?,
    private val followingCount: Int,
    private val followersCount: Int,
    private val creationDate: String
) : Abstract.Object<UserDetailsUi, UserDetailsDomainToUiMapper> {
    override fun map(mapper: UserDetailsDomainToUiMapper) =
        mapper.map(name, email, organization, followingCount, followersCount, creationDate)
}