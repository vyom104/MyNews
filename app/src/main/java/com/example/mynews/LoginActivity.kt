package com.example.mynews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    val etEmailAddress=findViewById<EditText>(R.id.etEmailAddress)
    val etPassword=findViewById<EditText>(R.id.etPassword)
    val btnLogin=findViewById<Button>(R.id.btnLogin)
    val textSignup=findViewById<TextView>(R.id.textSignup)
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth= FirebaseAuth.getInstance()
        btnLogin.setOnClickListener {
            loginUser()
        }
        textSignup.setOnClickListener {
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser() {
        val email=etEmailAddress.text.toString()
        val password=etPassword.text.toString()
        if(email.isBlank() || password.isBlank() ){
            Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener (this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Error in logging in", Toast.LENGTH_SHORT).show()
                }
            }
    }
}