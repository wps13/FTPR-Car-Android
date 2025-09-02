package com.example.myapitest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapitest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        binding.backButton.setOnClickListener { finish() }
        binding.saveButton.setOnClickListener { saveCar() }
        binding.deleteButton.setOnClickListener { deleteCar() }

    }


    private fun saveCar() {
        TODO("Not yet implemented")
    }

    private fun deleteCar() {
        TODO("Not yet implemented")
    }


    companion object {
        fun newIntent(context: Context) = Intent(context, DetailActivity::class.java)
    }
}