package com.veselovvv.githubusers.presentation.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.GitHubUsersApp
import com.veselovvv.githubusers.core.Retry
import de.hdodenhof.circleimageview.CircleImageView

class UserDetailsFragment : Fragment() {
    private lateinit var viewModel: UsersDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as GitHubUsersApp).getUserDetailsViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_user_details)
        toolbar.title = viewModel.getUserLogin()
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        val login = viewModel.getUserLogin()
        val avatarUrl = viewModel.getUserAvatarUrl()
        val photoImageView = requireActivity().findViewById<CircleImageView>(R.id.photoImageView)
        Picasso.get().load(avatarUrl.toUri()).into(photoImageView)

        viewModel.observe(this) {
            it.map(requireActivity().findViewById(R.id.progress_layout))
            it.map(
                requireActivity().findViewById(R.id.nameTextView),
                requireActivity().findViewById(R.id.emailTextView),
                requireActivity().findViewById(R.id.organizationTextView),
                requireActivity().findViewById(R.id.followingCountTextView),
                requireActivity().findViewById(R.id.followersCountTextView),
                requireActivity().findViewById(R.id.creationDateTextView)
            )
            it.map(
                requireActivity().findViewById(R.id.fail_layout),
                requireActivity().findViewById(R.id.messageTextView),
                requireActivity().findViewById(R.id.tryAgainButton),
                object : Retry {
                    override fun tryAgain() = viewModel.fetchUserDetails(login)
                }
            )
        }
        viewModel.fetchUserDetails(login)
    }
}