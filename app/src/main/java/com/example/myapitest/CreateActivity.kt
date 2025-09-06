package com.example.myapitest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapitest.databinding.ActivityCreateBinding
import com.example.myapitest.model.CarLocation
import com.example.myapitest.model.CarValue
import com.example.myapitest.service.Result
import com.example.myapitest.service.RetrofitClient
import com.example.myapitest.service.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.SecureRandom

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        binding.addButton.setOnClickListener { onAddPressed() }
        binding.CancelButton.setOnClickListener { onCancelPressed() }
    }

    private fun onAddPressed() {
        val model = binding.model.text.toString()
        val year = binding.year.text.toString()
        val licence = binding.licence.text.toString()

        val areAllFieldsValid = validateForm()

        if (!areAllFieldsValid) {
            return
        }

        val car = CarValue(
            SecureRandom().nextInt().toString(),
            // TODO - Update later
            "https://image",
            year,
            model,
            licence,
            CarLocation(
                long = 0.0,
                lat = 0.0
            )
        )

        CoroutineScope(Dispatchers.IO).launch {

            val result = safeApiCall { RetrofitClient.apiService.addCar(car) }

            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        Toast
                            .makeText(
                                this@CreateActivity,
                                R.string.create_success,
                                Toast.LENGTH_SHORT
                            ).show()
                        finish()
                    }

                    is Result.Error -> {
                        Toast
                            .makeText(
                                this@CreateActivity,
                                R.string.create_error,
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                }
            }


        }
    }

    private fun onCancelPressed() {
        finish()
    }

    private fun validateForm() : Boolean {
        val model = binding.model.text.toString()
        val year = binding.year.text.toString()
        val licence = binding.licence.text.toString()

        val areAllFieldsValid = model.isNotEmpty() && year.isNotEmpty() && licence.isNotEmpty()

        if(areAllFieldsValid) {
            binding.model.error = null
            binding.year.error = null
            binding.licence.error = null
            return true
        }

        if (model.isEmpty()) {
            binding.model.error = getString(R.string.required_field)

        }
        if (year.isEmpty()) {
            binding.year.error = getString(R.string.required_field)

        }
        if (licence.isEmpty()) {
            binding.licence.error = getString(R.string.required_field)

        }

        return false
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, CreateActivity::class.java)
    }
}