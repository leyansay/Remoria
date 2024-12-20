package com.example.remoria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.remoria.databinding.ActivitySlamUserListBinding

class SlamInfoAdapter(private val slamInfoList: List<SlamInfo>) :
    RecyclerView.Adapter<SlamInfoAdapter.SlamInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamInfoViewHolder {
        val binding = ActivitySlamUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlamInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlamInfoViewHolder, position: Int) {
        val slamInfo = slamInfoList[position]
        holder.bind(slamInfo)
    }

    override fun getItemCount(): Int {
        return slamInfoList.size
    }

    inner class SlamInfoViewHolder(private val binding: ActivitySlamUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
}
