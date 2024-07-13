package com.example.satechnologies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.satechnologies.databinding.ActivityRegistrationBinding
import com.example.satechnologies.viewmodel.LoginViewModel
import com.example.satechnologies.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val registerViewModel: RegistrationViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view: View = binding.getRoot()
        setContentView(view)

        binding.buttonSubmit.setOnClickListener {
            if (binding.editTextEmail.text.toString().trim()
                    .isNotEmpty() && binding.editTextPassword.text.toString().trim().isNotEmpty()
            ) {
                registerViewModel.registerUser(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString()
                ).observe(this) { message ->
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    binding.editTextEmail.text?.clear()
                    binding.editTextPassword.text?.clear()
                }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonLogin.setOnClickListener {
            if (binding.editTextEmail.text.toString().trim()
                    .isNotEmpty() && binding.editTextPassword.text.toString().trim().isNotEmpty()
            ) {
                loginViewModel.loginUser(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString()
                ).observe(this) { message ->
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    binding.editTextEmail.text?.clear()
                    binding.editTextPassword.text?.clear()
                    if (message.equals("login was successful")) {
                        val intent = Intent(this, InspectionActivity::class.java)
                        startActivity(intent)
                    }

                }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
