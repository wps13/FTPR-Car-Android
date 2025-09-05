package com.example.myapitest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapitest.databinding.ActivityDetailBinding
import com.example.myapitest.model.CarValue
import com.example.myapitest.service.Result
import com.example.myapitest.service.RetrofitClient
import com.example.myapitest.service.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var car: CarValue

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
        loadItem()
    }


    private fun saveCar() {
        TODO("Not yet implemented")
    }

    private fun deleteCar() {
        TODO("Not yet implemented")
    }

    private fun loadItem(){
        val itemId = intent.getStringExtra(ITEM_ID) ?: ""
        CoroutineScope(Dispatchers.IO).launch {
            val result = safeApiCall { RetrofitClient.apiService.getCar(itemId) }

            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        val carData = result.data
                        car = carData.value
                        handleSuccess()
                    }
                    is Result.Error -> handleError()
                }
            }
            Log.d("Hello World", "Carregou o Detalhe de $result")
        }
    }

    private fun handleSuccess() {
        binding.model.text = Editable.Factory.getInstance().newEditable(car.name)
        binding.year.text = Editable.Factory.getInstance().newEditable(car.year)
        binding.license.text = car.license
    }

    private fun handleError() {
        Toast.makeText(this, "Error loading item", Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val ITEM_ID = "item_id"
        fun newIntent(
            context: Context, itemId: String,
        ) = Intent(context, DetailActivity::class.java).apply {
            putExtra(ITEM_ID, itemId)
        }
    }
}