package com.veselovvv.githubusers.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.GitHubUsersApp
import com.veselovvv.githubusers.core.Retry

class UsersFragment : Fragment() {
    private lateinit var viewModel: UsersViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as GitHubUsersApp).getUsersViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

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

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        viewModel.observe(this, { adapter.update(it) })
        viewModel.init()
    }
}