package com.veselovvv.githubusers.servicelocator.core

import androidx.lifecycle.ViewModel

interface BaseModule<T : ViewModel> {
    fun getViewModel(): T
}