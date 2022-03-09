package com.veselovvv.githubusers.presentation.users

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.githubusers.core.Save
import com.veselovvv.githubusers.domain.users.UsersDomainToUiMapper
import com.veselovvv.githubusers.domain.users.UsersInteractor
import com.veselovvv.githubusers.presentation.main.NavigationCommunication
import com.veselovvv.githubusers.presentation.main.Screens.Companion.USERS_SCREEN
import com.veselovvv.githubusers.presentation.main.Screens.Companion.USER_DETAILS_SCREEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(
    private val usersInteractor: UsersInteractor,
    private val mapper: UsersDomainToUiMapper,
    private val communication: UsersCommunication,
    private val userCache: Save<Pair<String, String>>,
    private val navigator: Save<Int>,
    private val navigationCommunication: NavigationCommunication
) : ViewModel() {
    fun fetchUsers() {
        communication.map(listOf(UserUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = usersInteractor.fetchUsers()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<UserUi>>) =
        communication.observe(owner, observer)

    fun showUser(login: String, avatarUrl: String) {
        userCache.save(Pair(login, avatarUrl))
        navigationCommunication.map(USER_DETAILS_SCREEN)
    }

    fun init() {
        navigator.save(USERS_SCREEN)
        fetchUsers()
    }
}