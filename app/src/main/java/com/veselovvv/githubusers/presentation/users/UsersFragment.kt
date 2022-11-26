package com.veselovvv.githubusers.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.GitHubUsersApp
import com.veselovvv.githubusers.core.Retry

class UsersFragment : Fragment() {
    private lateinit var viewModel: UsersViewModel
    private lateinit var swipeToRefreshLayout: SwipeRefreshLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as GitHubUsersApp).getUsersViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_users, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GitHubUsersAdapter(
            object : Retry {
                override fun tryAgain() = viewModel.fetchUsers()
            },
            object : GitHubUsersAdapter.UserListener {
                override fun showUser(login: String, avatarUrl: String) =
                    viewModel.showUser(login, avatarUrl)
            }
        )

        toolbar = view.findViewById(R.id.toolbar_users)
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.users_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_search -> {
                    val searchView = it.actionView as SearchView
                    searchView.queryHint = getString(R.string.search_hint)
                    searchView.setOnQueryTextListener(
                        SimpleQueryListener.Base { query ->
                            viewModel.searchUsers(query.toString())
                        }
                    )
                    true
                }
                else -> false
            }
        }

        swipeToRefreshLayout = view.findViewById(R.id.swipeToRefresh)
        swipeToRefreshLayout.setOnRefreshListener {
            viewModel.observe(this) { adapter.update(it) }
            viewModel.init()
            swipeToRefreshLayout.isRefreshing = false
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewModel.observe(this) { adapter.update(it) }
        viewModel.init()
    }

    override fun onStart() {
        super.onStart()
        if (toolbar.menu.isEmpty()) toolbar.inflateMenu(R.menu.users_menu)
    }

    override fun onPause() {
        super.onPause()
        toolbar.menu.clear()
    }
}