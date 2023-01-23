package com.example.allbunchofapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.allbunchofapp.databinding.ActivityLoginBinding
import com.example.allbunchofapp.util.Validator
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        binding.btnSignUp.setOnClickListener{
            Intent(this,RegistrationActivity::class.java).apply {
                startActivity(this)
            }
        }

        binding.btnLogin.setOnClickListener {
            if(Validator.isValidEmail(binding.etEmail,true)
                && Validator.isValidPassword(binding.etPassword)){
                firebaseAuth.signInWithEmailAndPassword(
                    binding.etEmail.text.toString(),binding.etPassword.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        sendUserToNextActivity()
                    }else{
                        Snackbar.make(binding.root,it.exception.toString(),
                            BaseTransientBottomBar.LENGTH_LONG
                        ).apply {
                            this.show()
                        }
                    }
                }
            }
        }
    }

    private fun sendUserToNextActivity() {
        Intent(this,DashboardActivity::class.java).apply {
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
    }
}