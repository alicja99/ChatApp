package com.alicja.chatapp.delegators.adapter.rows

import com.alicja.chatapp.R
import com.alicja.chatapp.model.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_messages_row.view.*

class UserItem (val user: User): Item<ViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.activity_new_messages_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.usernameTextViewNewMessageActivity.text = user.username
        if (user.photoUrl != null && user.photoUrl.isNotEmpty()) {
            Picasso.get().load(user.photoUrl).into(viewHolder.itemView.photoNewMessageActivity)
        }

    }

}