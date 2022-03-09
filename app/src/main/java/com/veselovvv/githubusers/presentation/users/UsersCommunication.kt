package com.veselovvv.githubusers.presentation.users

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.veselovvv.githubusers.core.Abstract

interface UsersCommunication : Abstract.Mapper {
    fun map(users: List<UserUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<UserUi>>)

    class Base : UsersCommunication {
        private val listLiveData = MutableLiveData<List<UserUi>>()

        override fun map(users: List<UserUi>) {
            listLiveData.value = users
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UserUi>>) =
            listLiveData.observe(owner, observer)
    }
}