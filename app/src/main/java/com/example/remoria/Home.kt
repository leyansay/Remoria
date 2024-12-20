package com.example.remoria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.remoria.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var recyclerView: RecyclerView
    private var slamInfoList: MutableList<SlamInfo> = mutableListOf()  // Mutable list to add user data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView with the empty list
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = SlamInfoAdapter(slamInfoList)  // Create the adapter
        recyclerView.adapter = adapter  // Set the adapter for the RecyclerView

        // Navigate to FillInfo activity when addButton is clicked
        binding.addButton.setOnClickListener {
            val intent = Intent(this, FillInfo::class.java)
            resultLauncher.launch(intent)  // Launch the activity for result
        }
    }

    // Registering the activity result launcher
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Extract SlamInfo data from the intent
            val slamInfo = result.data?.getParcelableExtra<SlamInfo>("slamInfo")

            // Check if slamInfo is not null
            slamInfo?.let {
                slamInfoList.add(it)  // Add the new SlamInfo to the list
                Log.d("HomeActivity", "Added SlamInfo: $slamInfo")  // Log the SlamInfo data
                recyclerView.adapter?.notifyDataSetChanged()  // Notify the adapter that data has changed
            }
        }
    }
}
