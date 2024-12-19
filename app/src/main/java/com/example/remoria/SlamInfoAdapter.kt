package com.example.remoria

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remoria.databinding.ActivitySlamUserListBinding

class SlamInfoAdapter(private val slamList: List<SlamInfo>) :
    RecyclerView.Adapter<SlamInfoAdapter.SlamViewHolder>() {

    class SlamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullName: TextView = itemView.findViewById(R.id.textFullname)
        val nickname: TextView = itemView.findViewById(R.id.textNickname)
        val zodiacSign: TextView = itemView.findViewById(R.id.textZodiac)
        val relationshipStatus: TextView = itemView.findViewById(R.id.statusDropdown)
        val color: TextView = itemView.findViewById(R.id.Color)
        val movieCharacter: TextView = itemView.findViewById(R.id.movieChar)
        val place: TextView = itemView.findViewById(R.id.Place)
        val favoriteQuote: TextView = itemView.findViewById(R.id.faveQoute)
        val bestDescription: TextView = itemView.findViewById(R.id.bestDes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_slam_user_list, parent, false)
        return SlamViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlamViewHolder, position: Int) {
        val item = slamList[position]
        holder.fullName.text = item.fullName
        holder.nickname.text = item.nickname
        holder.zodiacSign.text = item.zodiacSign
        holder.relationshipStatus.text = item.relationshipStatus
        holder.color.text = item.color
        holder.movieCharacter.text = item.movieCharacter
        holder.place.text = item.place
        holder.favoriteQuote.text = item.favoriteQuote
        holder.bestDescription.text = item.bestDescription

        // Set an OnClickListener for item clicks (optional)
        holder.itemView.setOnClickListener {
            navigateToDetails(holder.itemView.context, item)
        }
    }

    override fun getItemCount(): Int = slamList.size

    private fun navigateToDetails(context: Context, slamInfo: SlamInfo) {
        val intent = Intent(context, Home::class.java)
        intent.putExtra("fullName", slamInfo.fullName)
        intent.putExtra("nickname", slamInfo.nickname)
        intent.putExtra("zodiac sign", slamInfo.zodiacSign)
        intent.putExtra("relationship status", slamInfo.relationshipStatus)
        intent.putExtra("color", slamInfo.color)
        intent.putExtra("movie character", slamInfo.movieCharacter)
        intent.putExtra("place", slamInfo.place)
        intent.putExtra("favorite quote", slamInfo.favoriteQuote)
        intent.putExtra("best describe", slamInfo.bestDescription)
        // Add other fields to the intent as needed
        context.startActivity(intent)
    }
}