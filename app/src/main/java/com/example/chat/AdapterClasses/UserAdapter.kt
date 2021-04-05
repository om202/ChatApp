package com.example.chat.AdapterClasses

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.ModelClasses.Users
import com.example.chat.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.user_search_item_layout.view.*

class UserAdapter(private val mContext: Context, private val mUsers: ArrayList<Users>,
                  private val isChatCheck: Boolean
):RecyclerView.Adapter<UserAdapter.ViewHolder?>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var nameText: TextView = itemView.search_user_profile_name
        var profileImageView:CircleImageView = itemView.search_user_profile_image
//        var onlineText: TextView = itemView.search_user_profile_status

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(mContext).inflate(R.layout.user_search_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: Users = mUsers[position]
        holder.nameText.text = user.getname()
        Picasso.get().load(user.getprofile()).into(holder.profileImageView)
    }

    override fun getItemCount(): Int {
        return mUsers.count()
    }
}