package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.speechrecificationapp.view.ItemsActivity
import com.example.speechrecificationapp.view.UploadActivity

class Speech_Therapist_Activity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech__therapist_)

        val therapist_back_id = findViewById<ImageView>(R.id.therapist_back)
        therapist_back_id.setOnClickListener{
            val intent= Intent(this@Speech_Therapist_Activity, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val about_id = findViewById<ImageView>(R.id.about)
        about_id.setOnClickListener{
            val intent= Intent(this@Speech_Therapist_Activity, About::class.java)
            startActivity(intent)
            finish()
        }

        val logout_id = findViewById<ImageView>(R.id.logout)
        logout_id.setOnClickListener{
            val intent= Intent(this@Speech_Therapist_Activity, second_screen::class.java)
            startActivity(intent)
            finish()
        }

        val btnViewAll_id = findViewById<Button>(R.id.btnViewAll)
        //val btnAddInfo_id = findViewById<Button>(R.id.btnAddInfo)

        btnViewAll_id.setOnClickListener {
            startActivity(Intent(this, ItemsActivity::class.java))
        }
//        btnAddInfo_id.setOnClickListener {
//            startActivity(Intent(this, UploadActivity::class.java))
//        }
    }
}