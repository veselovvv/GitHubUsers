package com.veselovvv.githubusers.presentation.userdetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veselovvv.githubusers.core.Read
import com.veselovvv.githubusers.domain.userdetails.UsersDetailsDomainToUiMapper
import com.veselovvv.githubusers.domain.userdetails.UsersDetailsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersDetailsViewModel(
    private val usersDetailsInteractor: UsersDetailsInteractor,
    private val usersDetailsCommunication: UsersDetailsCommunication,
    private val usersDetailsMapper: UsersDetailsDomainToUiMapper,
    private val userCache: Read<Pair<String, String>>
) : ViewModel() {
    fun fetchUserDetails(login: String) {
        usersDetailsCommunication.map(UserDetailsUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val userDetails = usersDetailsInteractor.fetchUserDetails(login)
            val userDetailsUi = userDetails.map(usersDetailsMapper)
            withContext(Dispatchers.Main) {
                userDetailsUi.map(usersDetailsCommunication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<UserDetailsUi>) =
        usersDetailsCommunication.observe(owner, observer)

    fun getUserLogin() = userCache.read().first
    fun getUserAvatarUrl() = userCache.read().second
}