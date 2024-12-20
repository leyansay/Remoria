package com.example.remoria

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.remoria.databinding.ActivityFillInfoBinding

class FillInfo : AppCompatActivity() {

    private lateinit var binding: ActivityFillInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFillInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.fillInfoLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize dropdown adapters
        val zodiacAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.zodiac_signs,
            android.R.layout.simple_spinner_item
        )
        zodiacAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.zodiacDropdown.setAdapter(zodiacAdapter)

        val relationshipAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.relationship_stat,
            android.R.layout.simple_spinner_item
        )
        relationshipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.statusDropdown.setAdapter(relationshipAdapter)

        // Back button listener
        binding.backbtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Discard Changes?")
                .setMessage("Are you sure you want to discard the changes?")
                .setPositiveButton("Yes") { dialog, _ ->
                    dialog.dismiss()
                    startActivity(Intent(this, Home::class.java))
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        // Save button listener
        binding.saveInfo.setOnClickListener {
            val fullName = binding.textFullname.editText?.text.toString().trim()
            val nickname = binding.textNickname.editText?.text.toString().trim()
            val zodiacSign = binding.zodiacDropdown.text?.toString()?.trim() ?: ""
            val relationshipStatus = binding.statusDropdown.text?.toString()?.trim() ?: ""
            val color = binding.Color.editText?.text.toString().trim()
            val movieCharacter = binding.movieChar.editText?.text.toString().trim()
            val place = binding.Place.editText?.text.toString().trim()
            val favoriteQuote = binding.faveQoute.editText?.text.toString().trim()
            val bestDescription = binding.bestDes.editText?.text.toString().trim()

            // Validate fields
            if (fullName.isEmpty() || nickname.isEmpty() ||
                zodiacSign.isEmpty() || relationshipStatus.isEmpty() ||
                color.isEmpty() || movieCharacter.isEmpty() || place.isEmpty() ||
                favoriteQuote.isEmpty() || bestDescription.isEmpty()) {

                Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create SlamInfo object and navigate to Home
            val slamInfo = SlamInfo(
                fullName = fullName,
                nickname = nickname,
                zodiacSign = zodiacSign,
                relationshipStatus = relationshipStatus,
                color = color,
                movieCharacter = movieCharacter,
                place = place,
                favoriteQuote = favoriteQuote,
                bestDescription = bestDescription
            )

            val intent = Intent(this, Home::class.java)
            intent.putExtra("slam_info", slamInfo) // Ensure SlamInfo implements Parcelable
            startActivity(intent)
        }
    }
}
