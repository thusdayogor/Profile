package com.example.profile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profile.R
import com.example.profile.db.structure.ProfileModel
import com.example.profile.screens.start.StartFragment
import kotlinx.android.synthetic.main.item_profile.view.*

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>()
{
    var listProfile = emptyList<ProfileModel>()

    class ProfileViewHolder(view: View):RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_profile,parent,false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.itemView.item_email.text = listProfile[position].email
        holder.itemView.item_phone.text = listProfile[position].phone
    }

    override fun getItemCount(): Int {
       return listProfile.size
    }

    override fun onViewDetachedFromWindow(holder: ProfileViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }


    override fun onViewAttachedToWindow(holder: ProfileViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener{
            StartFragment.clickProfile(listProfile[holder.adapterPosition])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<ProfileModel>)
    {
        listProfile = list
        notifyDataSetChanged()
    }

}