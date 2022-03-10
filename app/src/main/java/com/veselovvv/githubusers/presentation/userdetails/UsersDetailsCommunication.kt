package com.veselovvv.githubusers.presentation.userdetails

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.githubusers.core.Abstract

interface UsersDetailsCommunication : Abstract.Mapper {
    fun map(userDetails: UserDetailsUi)
    fun observe(owner: LifecycleOwner, observer: Observer<UserDetailsUi>)

    class Base : UsersDetailsCommunication {
        private val liveData = MutableLiveData<UserDetailsUi>()

        override fun map(userDetails: UserDetailsUi) {
            liveData.value = userDetails
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<UserDetailsUi>) =
            liveData.observe(owner, observer)
    }
}