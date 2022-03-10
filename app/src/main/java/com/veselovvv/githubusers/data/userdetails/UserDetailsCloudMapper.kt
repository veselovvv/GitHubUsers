package com.veselovvv.githubusers.data.userdetails

import com.veselovvv.githubusers.core.Abstract

interface UserDetailsCloudMapper : Abstract.Mapper {
    fun map(cloud: Abstract.Object<UserDetailsData, ToUserDetailsMapper>): UserDetailsData

    class Base(private val userDetailsMapper: ToUserDetailsMapper) : UserDetailsCloudMapper {
        override fun map(cloud: Abstract.Object<UserDetailsData, ToUserDetailsMapper>) =
            cloud.map(userDetailsMapper)
    }
}