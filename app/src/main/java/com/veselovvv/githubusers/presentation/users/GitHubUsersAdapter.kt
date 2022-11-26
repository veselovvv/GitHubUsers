package com.veselovvv.githubusers.presentation.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.veselovvv.githubusers.R
import com.veselovvv.githubusers.core.Retry
import de.hdodenhof.circleimageview.CircleImageView

class GitHubUsersAdapter(
    private val retry: Retry,
    private val userListener: UserListener
) : RecyclerView.Adapter<GitHubUsersAdapter.GitHubUsersViewHolder>() {
    private val users = ArrayList<UserUi>()

    fun update(new: List<UserUi>) {
        users.clear()
        users.addAll(new)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (users[position]) {
        is UserUi.Base -> 0
        is UserUi.Fail -> 1
        is UserUi.Progress -> 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        0 -> GitHubUsersViewHolder.Base(R.layout.user_layout.makeView(parent), userListener)
        1 -> GitHubUsersViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent), retry)
        else -> GitHubUsersViewHolder.FullscreenProgress(
            R.layout.progress_fullscreen.makeView(parent)
        )
    }

    override fun onBindViewHolder(holder: GitHubUsersViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount() = users.size

    abstract class GitHubUsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun bind(user: UserUi) = Unit

        class FullscreenProgress(view: View) : GitHubUsersViewHolder(view)

        class Base(view: View, private val userListener: UserListener) : GitHubUsersViewHolder(view) {
            private val avatarImageView = itemView.findViewById<CircleImageView>(R.id.avatarImageView)
            private val loginTextView = itemView.findViewById<TextView>(R.id.loginTextView)
            private val idTextView = itemView.findViewById<TextView>(R.id.idTextView)

            override fun bind(user: UserUi) {
                user.map(object : UserUi.BaseMapper {
                    override fun map(id: Int, login: String, avatarUrl: String) {
                        Picasso.get().load(avatarUrl.toUri()).into(avatarImageView)
                        loginTextView.text = login
                        idTextView.text = id.toString()
                    }
                    override fun map(text: String) = Unit
                })

                itemView.setOnClickListener {
                    user.open(userListener)
                }
            }
        }

        class Fail(view: View, private val retry: Retry) : GitHubUsersViewHolder(view) {
            private val message = itemView.findViewById<TextView>(R.id.messageTextView)
            private val button = itemView.findViewById<Button>(R.id.tryAgainButton)

            override fun bind(user: UserUi) {
                user.map(object : UserUi.BaseMapper {
                    override fun map(text: String) {
                        message.text = text
                    }
                    override fun map(id: Int, login: String, avatarUrl: String) = Unit
                })
                button.setOnClickListener { retry.tryAgain() }
            }
        }
    }

    interface UserListener {
        fun showUser(login: String, avatarUrl: String)
    }
}

private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)

