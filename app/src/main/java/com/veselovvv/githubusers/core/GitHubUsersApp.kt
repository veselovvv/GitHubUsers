package com.veselovvv.githubusers.core

import android.app.Application
import com.veselovvv.githubusers.servicelocator.core.CoreModule
import com.veselovvv.githubusers.servicelocator.userdetails.UserDetailsModule
import com.veselovvv.githubusers.servicelocator.users.UsersModule

class GitHubUsersApp : Application() {
    private val coreModule = CoreModule()
    private val usersModule = UsersModule(coreModule)
    private val userDetailsModule = UserDetailsModule(coreModule)

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun getMainViewModel() = coreModule.getViewModel()
    fun getUsersViewModel() = usersModule.getViewModel()
    fun getUserDetailsViewModel() = userDetailsModule.getViewModel()
}