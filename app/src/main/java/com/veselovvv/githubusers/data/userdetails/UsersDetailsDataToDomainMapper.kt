package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.domain.userdetails.UsersDetailsDomain
import java.lang.Exception

interface UsersDetailsDataToDomainMapper : Abstract.Mapper {
    fun map(userDetails: UserDetailsData): UsersDetailsDomain
    fun map(exception: Exception): UsersDetailsDomain
}