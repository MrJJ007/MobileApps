package com.example.myapplicationattempt8

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun main(view: View){
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
    fun register(view: View){
        this.finish()
    }

}