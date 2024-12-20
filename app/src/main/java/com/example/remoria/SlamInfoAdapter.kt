package com.example.remoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.remoria.databinding.ActivitySlamUserListBinding

class SlamInfoAdapter(private val slamInfoList: MutableList<SlamInfo>) :
    RecyclerView.Adapter<SlamInfoAdapter.SlamInfoViewHolder>() {

    // Create a ViewHolder for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamInfoViewHolder {
        val binding = ActivitySlamUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlamInfoViewHolder(binding)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: SlamInfoViewHolder, position: Int) {
        val slamInfo = slamInfoList[position]
        holder.bind(slamInfo)
    }

    // Return the total number of items in the list
    override fun getItemCount(): Int {
        return slamInfoList.size
    }

    // ViewHolder to bind the slamInfo to the UI
    inner class SlamInfoViewHolder(private val binding: ActivitySlamUserListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(slamInfo: SlamInfo) {
            binding.Name.text = slamInfo.fullName
            binding.nickName.text = slamInfo.nickname
            binding.nickName3.text = slamInfo.zodiacSign
            binding.nickName4.text = slamInfo.relationshipStatus
            binding.nickName5.text = slamInfo.color
            binding.nickName6.text = slamInfo.movieCharacter
            binding.nickName7.text = slamInfo.place
            binding.nickName8.text = slamInfo.favoriteQuote
            binding.nickName9.text = slamInfo.bestDescription
        }
    }

    // Add new SlamInfo data to the list
    fun addSlamInfo(slamInfo: SlamInfo) {
        slamInfoList.add(slamInfo)
        notifyItemInserted(slamInfoList.size - 1)
    }
}
