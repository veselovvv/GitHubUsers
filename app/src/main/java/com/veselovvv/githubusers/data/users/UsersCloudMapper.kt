package com.veselovvv.githubusers.data.users

import com.veselovvv.githubusers.core.Abstract

interface UsersCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<Abstract.Object<UserData, ToUserMapper>>): List<UserData>

    class Base(private val userMapper: ToUserMapper) : UsersCloudMapper {
        override fun map(cloudList: List<Abstract.Object<UserData, ToUserMapper>>) =
            cloudList.map { userCloud -> userCloud.map(userMapper) }
    }
}