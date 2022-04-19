package com.veselovvv.githubusers.presentation.users

import androidx.appcompat.widget.SearchView

class SimpleQueryListener(private val viewModel: UsersViewModel) : SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?) = find(query)
    override fun onQueryTextChange(newText: String?) = find(newText)

    private fun find(query: String?): Boolean {
        viewModel.searchUsers(query.toString())
        return !query.isNullOrEmpty()
    }
}