package com.example.remoria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remoria.databinding.ActivityHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: SlamInfoAdapter
    private var slamInfoList: MutableList<SlamInfo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve and display the username from SharedPreferences
        val sharedPreferences = getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User")
        binding.textView4.text = "Hi $username!"

        // Handle Window Insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Load data from SharedPreferences
        slamInfoList = loadSlamInfo()

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = SlamInfoAdapter(slamInfoList)
        binding.recyclerView.adapter = adapter

        // Navigate to FillInfo activity when addButton is clicked
        binding.addButton.setOnClickListener {
            val intent = Intent( this, FillInfo::class.java)
            resultLauncher.launch(intent)
        }
    }

    // Registering the activity result launcher
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val slamInfo = result.data?.getParcelableExtra<SlamInfo>("slamInfo")
            slamInfo?.let {
                slamInfoList.add(it)
                saveSlamInfo(slamInfoList) // Save updated list to SharedPreferences
                adapter.notifyItemInserted(slamInfoList.size - 1)
                Log.d("HomeActivity", "Added SlamInfo: $slamInfo")
            }
        }
    }

    // Load SlamInfo list from SharedPreferences
    private fun loadSlamInfo(): MutableList<SlamInfo> {
        val sharedPreferences = getSharedPreferences("SLAM_PREFS", Context.MODE_PRIVATE)
        val gson = Gson()
        val currentListJson = sharedPreferences.getString("slam_list", null)
        val type = object : TypeToken<MutableList<SlamInfo>>() {}.type
        return gson.fromJson(currentListJson, type) ?: mutableListOf()
    }

    // Save SlamInfo list to SharedPreferences
    private fun saveSlamInfo(slamInfoList: MutableList<SlamInfo>) {
        val sharedPreferences = getSharedPreferences("SLAM_PREFS", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(slamInfoList)
        sharedPreferences.edit().putString("slam_list", json).apply()
    }
}
