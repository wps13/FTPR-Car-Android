package com.example.myapitest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.myapitest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onInit()
    }

    private fun onInit() {
        binding.loginPhone.addTextChangedListener { onLoginPhoneChanged() }
        binding.loginCode.addTextChangedListener { onLoginCodeChanged() }
        binding.submitButton.setOnClickListener { onSubmitPressed() }
    }

    private fun onLoginPhoneChanged() {
        val phone = binding.loginPhone.text.toString()
        checkPhoneError(phone)

    }

    private fun onLoginCodeChanged() {
        val code = binding.loginCode.text.toString()
        checkCodeError(code)
    }

    private fun checkCodeError(code: String): Boolean {
        if (code.isEmpty()) {
            binding.loginCode.error = "Campo obrigatório"
            return true
        }
        return false
    }

    private fun checkPhoneError(phone: String): Boolean {
        if (phone.isEmpty()) {
            binding.loginPhone.error = "Campo obrigatório"
            return true
        }
        if (!phone.isDigitsOnly()) {
            binding.loginPhone.error = "Apenas números"
            return true
        }
        return false
    }

    private fun onSubmitPressed() {
        val phone = binding.loginPhone.text.toString()
        val code = binding.loginCode.text.toString()
        val isPhoneError = checkPhoneError(phone)
        val isCodeError = checkCodeError(code)
        if (!isPhoneError && !isCodeError) {
            //TODO
        }
    }

}