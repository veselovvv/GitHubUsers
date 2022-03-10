package com.veselovvv.githubusers.data.userdetails.net

import com.google.gson.annotations.SerializedName
import com.veselovvv.githubusers.core.Abstract
import com.veselovvv.githubusers.data.userdetails.ToUserDetailsMapper
import com.veselovvv.githubusers.data.userdetails.UserDetailsData

data class UserDetailsCloud(
    @SerializedName("name")
    private val name: String,
    @SerializedName("email")
    private val email: String?,
    @SerializedName("company")
    private val organization: String?,
    @SerializedName("following")
    private val followingCount: Int,
    @SerializedName("followers")
    private val followersCount: Int,
    @SerializedName("created_at")
    private val creationDate: String
) : Abstract.Object<UserDetailsData, ToUserDetailsMapper> {
    override fun map(mapper: ToUserDetailsMapper) =
        mapper.map(name, email, organization, followingCount, followersCount, creationDate)
}