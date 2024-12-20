package com.example.remoria

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.remoria.databinding.ActivitySlamUserListBinding

class SlamInfoAdapter(private val slamInfoList: MutableList<SlamInfo>) :
    RecyclerView.Adapter<SlamInfoAdapter.SlamInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamInfoViewHolder {
        val binding = ActivitySlamUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlamInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlamInfoViewHolder, position: Int) {
        val slamInfo = slamInfoList[position]
        holder.bind(slamInfo, position)
    }

    override fun getItemCount(): Int {
        return slamInfoList.size
    }

    inner class SlamInfoViewHolder(private val binding: ActivitySlamUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(slamInfo: SlamInfo, position: Int) {
            binding.Name.text = slamInfo.fullName
            binding.nickName.text = slamInfo.nickname
            binding.nickName3.text = slamInfo.zodiacSign
            binding.nickName4.text = slamInfo.relationshipStatus
            binding.nickName5.text = slamInfo.color
            binding.nickName6.text = slamInfo.movieCharacter
            binding.nickName7.text = slamInfo.place
            binding.nickName8.text = slamInfo.favoriteQuote
            binding.nickName9.text = slamInfo.bestDescription

            // Set up the click listener for the trash button
            binding.trashbtn.setOnClickListener {
                // Show a confirmation dialog
                val builder = AlertDialog.Builder(binding.root.context)
                builder.setTitle("Delete Confirmation")
                builder.setMessage("Are you sure you want to delete this item?")
                builder.setPositiveButton("Yes") { dialog, _ ->
                    dialog.dismiss()
                    // Remove the item from the list
                    slamInfoList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, slamInfoList.size)
                    Toast.makeText(binding.root.context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss() // Close the dialog without doing anything
                }
                builder.create().show()
            }
        }
    }
}
