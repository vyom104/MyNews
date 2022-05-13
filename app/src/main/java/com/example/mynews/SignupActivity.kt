package com.example.mynews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    val etEmailSignup=findViewById<EditText>(R.id.etEmailSignup)
    val etPasswordSignup=findViewById<EditText>(R.id.etPasswordSignup)
    val btnSignup=findViewById<Button>(R.id.btnSignup)
    val etConfirmPassword=findViewById<EditText>(R.id.etConfirmPassword)
    val textLogin=findViewById<TextView>(R.id.textLogin)
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth= FirebaseAuth.getInstance()
        btnSignup.setOnClickListener {
            signupUser()
        }
        textLogin.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun signupUser(){
        val email=etEmailSignup.text.toString()
        val password=etPasswordSignup.text.toString()
        val confirmPassword=etConfirmPassword.text.toString()
        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
            Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmPassword){
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener (this){
                    if(it.isSuccessful){
                        Toast.makeText(this, "Account Created Successfully", Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Error in creating user", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}