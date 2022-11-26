package com.veselovvv.githubusers.presentation.userdetails

import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.veselovvv.githubusers.core.Retry
import com.veselovvv.githubusers.presentation.main.UI

sealed class UserDetailsUi : UI {
    open fun map(progressLayout: FrameLayout) = Unit
    open fun map(
        nameTextView: TextView,
        emailTextView: TextView,
        organizationTextView: TextView,
        followingCountTextView: TextView,
        followersCountTextView: TextView,
        creationDateTextView: TextView
    ) = Unit
    open fun map(
        failLayout: LinearLayout, messageTextView: TextView, tryAgainButton: Button, retry: Retry
    ) = Unit

    object Progress : UserDetailsUi() {
        override fun map(progressLayout: FrameLayout) {
            progressLayout.makeVisible()
        }
    }

    class Base(
        private val name: String,
        private val email: String?,
        private val organization: String?,
        private val followingCount: Int,
        private val followersCount: Int,
        private val creationDate: String
    ) : UserDetailsUi() {
        override fun map(progressLayout: FrameLayout) {
            progressLayout.makeVisible(false)
        }

        override fun map(
            nameTextView: TextView,
            emailTextView: TextView,
            organizationTextView: TextView,
            followingCountTextView: TextView,
            followersCountTextView: TextView,
            creationDateTextView: TextView
        ) {
            nameTextView.text = name
            emailTextView.text = email ?: NO_EMAIL
            organizationTextView.text = organization ?: NO_ORGANIZATION
            followingCountTextView.text = followingCount.toString()
            followersCountTextView.text = followersCount.toString()
            creationDateTextView.text = creationDate.subSequence(0, 10)
        }

        companion object {
            private const val NO_EMAIL = "No email"
            private const val NO_ORGANIZATION = "No organization"
        }
    }

    class Fail(private val message: String) : UserDetailsUi() {
        override fun map(progressLayout: FrameLayout) {
            progressLayout.makeVisible(false)
        }

        override fun map(
            failLayout: LinearLayout, messageTextView: TextView, tryAgainButton: Button, retry: Retry
        ) {
            failLayout.makeVisible()
            messageTextView.text = message
            tryAgainButton.setOnClickListener {
                retry.tryAgain()
                failLayout.makeVisible(false)
            }
        }
    }
}
