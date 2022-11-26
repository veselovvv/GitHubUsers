package com.veselovvv.githubusers.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.GitHubUsersApp
import com.veselovvv.githubusers.presentation.main.Screens.Companion.USERS_SCREEN
import com.veselovvv.githubusers.presentation.main.Screens.Companion.USER_DETAILS_SCREEN
import com.veselovvv.githubusers.presentation.userdetails.UserDetailsFragment
import com.veselovvv.githubusers.presentation.users.UsersFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as GitHubUsersApp).getMainViewModel()
        viewModel.observe(this) {
            when (it) {
                USERS_SCREEN -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, UsersFragment())
                        .commit()
                }
                USER_DETAILS_SCREEN -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, UserDetailsFragment())
                        .addToBackStack(USER_DETAILS_FRAGMENT_NAME)
                        .commit()
                }
                else -> throw IllegalStateException("Screen id is undefined: $it")
            }
        }
        viewModel.init()
    }

    override fun onBackPressed() {
        if (viewModel.navigateBack()) super.onBackPressed()
    }

    companion object {
        private const val USER_DETAILS_FRAGMENT_NAME = "UserDetailsFragment"
    }
}