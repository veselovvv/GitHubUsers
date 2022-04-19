package com.veselovvv.githubusers.data.users.net

import com.google.gson.annotations.SerializedName
import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.data.users.ToUserMapper
import com.veselovvv.githubusers.data.users.UserData

data class UserCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("login")
    private val login: String,
    @SerializedName("avatar_url")
    private val avatarUrl: String
) : Abstract.Object<UserData, ToUserMapper> {
    val userLogin get() = this.login
    override fun map(mapper: ToUserMapper) = mapper.map(id, login, avatarUrl)
}
