package com.veselovvv.githubusers.domain.userdetails

import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.core.ErrorType
import com.veselovvv.githubusers.presentation.userdetails.UsersDetailsUi

interface UsersDetailsDomainToUiMapper : Abstract.Mapper {
    fun map(userDetails: UserDetailsDomain): UsersDetailsUi
    fun map(errorType: ErrorType): UsersDetailsUi
}