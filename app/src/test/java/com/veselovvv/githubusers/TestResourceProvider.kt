package com.veselovvv.githubusers

import com.veselovvv.githubusers.core.ResourceProvider

class TestResourceProvider : ResourceProvider {
    override fun getString(id: Int) = when (id) {
        R.string.no_connection_message -> NO_CONNECTION_MESSAGE
        R.string.service_unavailable_message -> SERVICE_UNAVAILABLE_MESSAGE
        else -> SOMETHING_WENT_WRONG
    }

    companion object {
        private const val NO_CONNECTION_MESSAGE = "No connection. Please try again!"
        private const val SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable. Please try again!"
        private const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again!"
    }
}