package com.veselovvv.githubusers.core

abstract class Abstract {
    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface Mapper {
        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        class Empty : Mapper
    }
}