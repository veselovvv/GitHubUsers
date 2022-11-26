package com.veselovvv.githubusers.presentation.users

import androidx.appcompat.widget.SearchView

interface SimpleQueryListener : SearchView.OnQueryTextListener {
    fun find(query: String?): Boolean

    class Base(private val search: (String?) -> Unit) : SimpleQueryListener {
        override fun onQueryTextSubmit(query: String?) = find(query)
        override fun onQueryTextChange(newText: String?) = find(newText)

        override fun find(query: String?): Boolean {
            search.invoke(query)
            return !query.isNullOrEmpty()
        }
    }
}