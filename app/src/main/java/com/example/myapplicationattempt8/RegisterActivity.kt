package com.example.myapplicationattempt8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


    }

    fun main(view: View){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
    fun login(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    override fun finish(){
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
    }
}