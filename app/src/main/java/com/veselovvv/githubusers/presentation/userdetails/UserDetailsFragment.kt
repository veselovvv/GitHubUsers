package com.veselovvv.githubusers.presentation.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.GitHubUsersApp
import com.veselovvv.githubusers.core.Retry
import de.hdodenhof.circleimageview.CircleImageView

class UserDetailsFragment : Fragment() {
    private lateinit var viewModel: UsersDetailsViewModel
    private lateinit var photoImageView: CircleImageView
    private lateinit var progressLayout: FrameLayout
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var organizationTextView: TextView
    private lateinit var followingCountTextView: TextView
    private lateinit var followersCountTextView: TextView
    private lateinit var creationDateTextView: TextView
    private lateinit var failLayout: LinearLayout
    private lateinit var messageTextView: TextView
    private lateinit var tryAgainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as GitHubUsersApp).getUserDetailsViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoImageView = requireActivity().findViewById(R.id.photoImageView)
        progressLayout = requireActivity().findViewById(R.id.progress_layout)
        nameTextView = requireActivity().findViewById(R.id.nameTextView)
        emailTextView = requireActivity().findViewById(R.id.emailTextView)
        organizationTextView = requireActivity().findViewById(R.id.organizationTextView)
        followingCountTextView = requireActivity().findViewById(R.id.followingCountTextView)
        followersCountTextView = requireActivity().findViewById(R.id.followersCountTextView)
        creationDateTextView = requireActivity().findViewById(R.id.creationDateTextView)
        failLayout = requireActivity().findViewById(R.id.fail_layout)
        messageTextView = requireActivity().findViewById(R.id.messageTextView)
        tryAgainButton = requireActivity().findViewById(R.id.tryAgainButton)

        val login = viewModel.getUserLogin()
        val avatarUrl = viewModel.getUserAvatarUrl()
        Picasso.get().load(avatarUrl.toUri()).into(photoImageView)

        viewModel.observe(this) {
            it.map(progressLayout)
            it.map(
                nameTextView,
                emailTextView,
                organizationTextView,
                followingCountTextView,
                followersCountTextView,
                creationDateTextView
            )
            it.map(
                failLayout, messageTextView, tryAgainButton,
                object : Retry {
                    override fun tryAgain() = viewModel.fetchUserDetails(login)
                }
            )
        }
        viewModel.fetchUserDetails(login)
    }
}