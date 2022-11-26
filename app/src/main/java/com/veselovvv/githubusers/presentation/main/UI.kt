package com.veselovvv.githubusers.presentation.main

import android.view.View

interface UI {
    fun View.makeVisible(visible: Boolean = true) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }
}