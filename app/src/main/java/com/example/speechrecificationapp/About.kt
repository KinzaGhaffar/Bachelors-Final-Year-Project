package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class About : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val about_back_id = findViewById<ImageView>(R.id.about_back)
        about_back_id.setOnClickListener {
            val intent= Intent(this@About, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val exit_id = findViewById<ImageView>(R.id.logout)
        exit_id.setOnClickListener {
            val intent= Intent(this@About, second_screen::class.java)
            startActivity(intent)
            finish()
        }
    }
}