package com.veselovvv.githubusers.presentation.users

import com.veselovvv.githubusers.core.Abstract

sealed class UserUi : Abstract.Object<Unit, UserUi.BaseMapper> {
    override fun map(mapper: BaseMapper) = Unit
    open fun open(userListener: GitHubUsersAdapter.UserListener) = Unit

    object Progress : UserUi()

    class Base(
        private val id: Int, private val login: String, private val avatarUrl: String
    ) : UserUi() {
        override fun map(mapper: BaseMapper) = mapper.map(id, login, avatarUrl)
        override fun open(userListener: GitHubUsersAdapter.UserListener) =
            userListener.showUser(login, avatarUrl)
    }

    class Fail(private val message: String) : UserUi() {
        override fun map(mapper: BaseMapper) = mapper.map(message)
    }

    interface BaseMapper : Abstract.Mapper {
        fun map(id: Int, login: String, avatarUrl: String)
        fun map(text: String)
    }
}