package com.veselovvv.githubusers.presentation.userdetails

import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.veselovvv.githubusers.core.Retry

sealed class UserDetailsUi {
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
            progressLayout.visibility = View.VISIBLE
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
            progressLayout.visibility = View.GONE
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
            emailTextView.text = email ?: "No email"
            organizationTextView.text = organization ?: "No organization"
            followingCountTextView.text = followingCount.toString()
            followersCountTextView.text = followersCount.toString()
            creationDateTextView.text = creationDate.subSequence(0, 10)
        }
    }

    class Fail(private val message: String) : UserDetailsUi() {
        override fun map(progressLayout: FrameLayout) {
            progressLayout.visibility = View.GONE
        }

        override fun map(failLayout: LinearLayout, messageTextView: TextView, tryAgainButton: Button, retry: Retry) {
            failLayout.visibility = View.VISIBLE
            messageTextView.text = message
            tryAgainButton.setOnClickListener {
                retry.tryAgain()
                failLayout.visibility = View.GONE
            }
        }
    }
}
