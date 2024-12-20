package com.example.remoria

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remoria.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: SlamInfoAdapter
    private var slamInfoList: MutableList<SlamInfo> = mutableListOf()  // Mutable list to add user data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra("username")
        binding.textView4.text = "Hi $username!"

        // Handle Window Insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize RecyclerView with the empty list
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SlamInfoAdapter(slamInfoList)  // Create the adapter
        binding.recyclerView.adapter = adapter  // Set the adapter for the RecyclerView

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
                adapter.notifyItemInserted(slamInfoList.size - 1)  // Notify the adapter that a new item has been added
            }
        }
    }
}
