package com.example.mynews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class Intro : AppCompatActivity() {
    val btnGetStarted=findViewById<Button>(R.id.btnGetStarted)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        val auth=FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            redirect("MAIN")
        }
         btnGetStarted.setOnClickListener {
             redirect("LOGIN")
         }

    }
    private fun redirect(name:String){
        val intent=when(name){
            "LOGIN" -> Intent(this,LoginActivity::class.java)
            "MAIN" -> Intent(this,MainActivity::class.java)
            else -> throw Exception("No path exists")
        }
        startActivity(intent)
        finish()
    }
}