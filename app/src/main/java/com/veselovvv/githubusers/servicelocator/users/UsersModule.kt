package com.veselovvv.githubusers.servicelocator.users

import com.veselovvv.githubusers.data.users.ToUserMapper
import com.veselovvv.githubusers.data.users.UsersCloudDataSource
import com.veselovvv.githubusers.data.users.UsersCloudMapper
import com.veselovvv.githubusers.data.users.UsersRepository
import com.veselovvv.githubusers.data.users.net.UsersService
import com.veselovvv.githubusers.domain.users.BaseUserDataToDomainMapper
import com.veselovvv.githubusers.domain.users.BaseUsersDataToDomainMapper
import com.veselovvv.githubusers.domain.users.UsersInteractor
import com.veselovvv.githubusers.presentation.users.BaseUserDomainToUiMapper
import com.veselovvv.githubusers.presentation.users.BaseUsersDomainToUiMapper
import com.veselovvv.githubusers.presentation.users.UsersCommunication
import com.veselovvv.githubusers.presentation.users.UsersViewModel
import com.veselovvv.githubusers.servicelocator.core.BaseModule
import com.veselovvv.githubusers.servicelocator.core.CoreModule

class UsersModule(private val coreModule: CoreModule) : BaseModule<UsersViewModel> {
    override fun getViewModel() = UsersViewModel(
        getUsersInteractor(),
        getUsersMapper(),
        getUsersCommunication(),
        coreModule.userCache,
        coreModule.navigator,
        coreModule.navigationCommunication
    )

    private fun getUsersInteractor() = UsersInteractor.Base(
        getUsersRepository(),
        BaseUsersDataToDomainMapper(BaseUserDataToDomainMapper())
    )

    private fun getUsersRepository() = UsersRepository.Base(
        getUsersCloudDataSource(),
        UsersCloudMapper.Base(ToUserMapper.Base())
    )

    private fun getUsersCloudDataSource() = UsersCloudDataSource.Base(getUsersService(), coreModule.gson)
    private fun getUsersService() = coreModule.makeService(UsersService::class.java)
    private fun getUsersMapper() =
        BaseUsersDomainToUiMapper(coreModule.resourceProvider, BaseUserDomainToUiMapper())
    private fun getUsersCommunication() = UsersCommunication.Base()
}