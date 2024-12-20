package com.example.remoria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.remoria.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    // Create a reference to the binding object
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if a username already exists
        val sharedPreferences = getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", null)
        if (username != null) {
            // Navigate to Home if username exists
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish() // Finish the Register activity to prevent going back to it
            return
        }

        // Initialize the binding object
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set click listener for "toHome" button using binding
        binding.toHome.setOnClickListener {
            val enteredUsername = binding.usernameEditText.text.toString().trim()

            if (enteredUsername.isNotEmpty()) {
                // Save the username to SharedPreferences
                sharedPreferences.edit().putString("username", enteredUsername).apply()

                // Navigate to Home activity
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish() // Prevent navigating back to the Register activity
            } else {
                binding.usernameEditText.error = "Please enter a username"
            }
        }
    }
}
