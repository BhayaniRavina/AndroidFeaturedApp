package com.example.allbunchofapp

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.allbunchofapp.databinding.ActivityRegistrationBinding
import com.example.allbunchofapp.util.Validator
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth

    private lateinit var binding : ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        binding.btnSignUp.setOnClickListener {
            PerformAuth()
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = firebaseAuth.currentUser
    }
    private fun PerformAuth() {
        if(Validator.isValidEmail(binding.etEmail,true)
            && Validator.isValidPassword(binding.etPassword)){
            binding.progressCircular.visibility = VISIBLE

            firebaseAuth.createUserWithEmailAndPassword(binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            ).addOnCompleteListener {
                if(it.isSuccessful){
                    openLoginActivity()
                    binding.progressCircular.visibility = INVISIBLE
                }else{
                    Snackbar.make(binding.root,it.exception.toString(),LENGTH_LONG).apply {
                        this.show()
                    }
                }
            }
        }
    }

    private fun openLoginActivity() {
        Intent(this,LoginActivity::class.java).apply {
            FLAG_ACTIVITY_CLEAR_TASK
            FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }
}