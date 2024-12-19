package com.example.remoria

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
        recyclerView.adapter = SlamInfoAdapter(slamInfoList)  // Pass the empty list to the adapter

        // Navigate to FillInfo activity when addButton is clicked
        binding.addButton.setOnClickListener {
            val intent = Intent(this, FillInfo::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_INFO)  // Requesting data from FillInfo
        }
    }

    // Handle the result from FillInfo activity (user adding data)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_INFO && resultCode == RESULT_OK) {
            // Extract the SlamInfo data from the intent
            val slamInfo = data?.getParcelableExtra<SlamInfo>("slamInfo")
            if (slamInfo != null) {
                slamInfoList.add(slamInfo)  // Add the new SlamInfo to the list
                recyclerView.adapter?.notifyDataSetChanged()  // Notify the adapter that data has changed
            }
        }
    }


    companion object {
        const val REQUEST_CODE_ADD_INFO = 1  // Request code for adding info
    }
}