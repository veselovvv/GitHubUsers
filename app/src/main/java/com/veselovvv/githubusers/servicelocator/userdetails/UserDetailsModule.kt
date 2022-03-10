package com.veselovvv.githubusers.servicelocator.userdetails

import com.veselovvv.githubusers.data.userdetails.ToUserDetailsMapper
import com.veselovvv.githubusers.data.userdetails.UserDetailsCloudDataSource
import com.veselovvv.githubusers.data.userdetails.UserDetailsCloudMapper
import com.veselovvv.githubusers.data.userdetails.UsersDetailsRepository
import com.veselovvv.githubusers.data.userdetails.net.UserDetailsService
import com.veselovvv.githubusers.domain.userdetails.BaseUserDetailsDataToDomainMapper
import com.veselovvv.githubusers.domain.userdetails.BaseUsersDetailsDataToDomainMapper
import com.veselovvv.githubusers.domain.userdetails.UsersDetailsInteractor
import com.veselovvv.githubusers.presentation.userdetails.BaseUserDetailsDomainToUiMapper
import com.veselovvv.githubusers.presentation.userdetails.BaseUsersDetailsDomainToUiMapper
import com.veselovvv.githubusers.presentation.userdetails.UsersDetailsCommunication
import com.veselovvv.githubusers.presentation.userdetails.UsersDetailsViewModel
import com.veselovvv.githubusers.servicelocator.core.BaseModule
import com.veselovvv.githubusers.servicelocator.core.CoreModule

class UserDetailsModule(private val coreModule: CoreModule) : BaseModule<UsersDetailsViewModel> {
    override fun getViewModel() = UsersDetailsViewModel(
        getUsersDetailsInteractor(),
        getUsersDetailsCommunication(),
        getUsersDetailsMapper(),
        coreModule.userCache
    )

    private fun getUsersDetailsInteractor() = UsersDetailsInteractor.Base(
        getUsersDetailsRepository(),
        BaseUsersDetailsDataToDomainMapper(BaseUserDetailsDataToDomainMapper())
    )

    private fun getUsersDetailsRepository() = UsersDetailsRepository.Base(
        getUserDetailsCloudDataSource(),
        UserDetailsCloudMapper.Base(ToUserDetailsMapper.Base())
    )

    private fun getUserDetailsCloudDataSource() =
        UserDetailsCloudDataSource.Base(getUserDetailsService(), coreModule.gson)
    private fun getUserDetailsService() = coreModule.makeService(UserDetailsService::class.java)
    private fun getUsersDetailsCommunication() = UsersDetailsCommunication.Base()
    private fun getUsersDetailsMapper() =
        BaseUsersDetailsDomainToUiMapper(coreModule.resourceProvider, BaseUserDetailsDomainToUiMapper())
}