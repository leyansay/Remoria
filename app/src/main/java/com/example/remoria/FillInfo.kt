package com.example.remoria

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.remoria.databinding.ActivityFillInfoBinding

class FillInfo : AppCompatActivity() {

    private lateinit var binding: ActivityFillInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize ViewBinding
        binding = ActivityFillInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.fillInfoLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize adapter for zodiac signs dropdown
        val zodiacAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.zodiac_signs,
            android.R.layout.simple_spinner_item
        )
        zodiacAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.zodiacDropdown.setAdapter(zodiacAdapter)

        // Set up the Relationship status Spinner
        val relationshipAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.relationship_stat,
            android.R.layout.simple_spinner_item
        )
        relationshipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.statusDropdown.setAdapter(relationshipAdapter)

        // Set up the click listener for the "backbtn" button
        binding.backbtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Discard Changes?")
            builder.setMessage("Are you sure you want to discard the changes?")
            builder.setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss() // Close the dialog without doing anything
            }
            builder.create().show() // Show the dialog
        }

        // Set up the Save button listener
        binding.saveInfo.setOnClickListener {
            // Collecting data from the user
            val fullName = binding.textFullname.editText?.text.toString()
            val nickname = binding.textNickname.editText?.text.toString()

            // Fetch selected values from Spinners
            val zodiacSign = binding.zodiacDropdown.text?.toString() ?: ""
            val relationshipStatus  = binding.statusDropdown.text?.toString() ?: ""

            // Collect other user input
            val color = binding.Color.editText?.text.toString()
            val movieCharacter = binding.movieChar.editText?.text.toString()
            val place = binding.Place.editText?.text.toString()
            val favoriteQuote = binding.faveQoute.editText?.text.toString()
            val bestDescription = binding.bestDes.editText?.text.toString()

            // Create a SlamInfo object with the collected data
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

            // Pass the SlamInfo object to the Home activity
            val intent = Intent(this, Home::class.java)
            intent.putExtra("slam_info", slamInfo) // Pass SlamInfo as Parcelable
            startActivity(intent)
        }
    }
}