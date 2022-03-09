package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.core.Abstract

interface ToUserMapper : Abstract.Mapper {
    fun map(id: Int, login: String, avatarUrl: String): UserData

    class Base : ToUserMapper {
        override fun map(id: Int, login: String, avatarUrl: String) = UserData(id, login, avatarUrl)
    }
}