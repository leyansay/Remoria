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

        binding.saveInfo.setOnClickListener {
            // Collect data
            val slamInfo = SlamInfo(
                fullName = binding.textFullname.editText?.text.toString().trim(),
                nickname = binding.textNickname.editText?.text.toString().trim(),
                zodiacSign = binding.zodiacDropdown.text?.toString()?.trim() ?: "",
                relationshipStatus = binding.statusDropdown.text?.toString()?.trim() ?: "",
                color = binding.Color.editText?.text.toString().trim(),
                movieCharacter = binding.movieChar.editText?.text.toString().trim(),
                place = binding.Place.editText?.text.toString().trim(),
                favoriteQuote = binding.faveQoute.editText?.text.toString().trim(),
                bestDescription = binding.bestDes.editText?.text.toString().trim()
            )

            // Validate that essential fields are not empty
            if (slamInfo.fullName.isEmpty() || slamInfo.nickname.isEmpty() ||
                slamInfo.zodiacSign.isEmpty() || slamInfo.relationshipStatus.isEmpty() ||
                slamInfo.color.isEmpty() || slamInfo.movieCharacter.isEmpty() || slamInfo.place.isEmpty() ||
                slamInfo.favoriteQuote.isEmpty() || slamInfo.bestDescription.isEmpty()) {

                Toast.makeText(this, "Fields must not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Pass data back to Home activity
            val resultIntent = Intent().apply {
                putExtra("slamInfo", slamInfo) // Assuming SlamInfo is Parcelable
            }
            setResult(RESULT_OK, resultIntent)
            finish() // Close FillInfo activity
        }
    }
}
