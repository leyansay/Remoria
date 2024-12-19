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


        // Initialize adapter for zodiac signs
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

        // Correctly access the EditText inside TextInputLayout
        val textFullname = binding.textFullname.editText?.text.toString()


        val resultIntent = Intent()
        resultIntent.putExtra("FULL_NAME_KEY", textFullname)
        setResult(RESULT_OK, resultIntent)
        finish()



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


        binding.saveInfo.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}
