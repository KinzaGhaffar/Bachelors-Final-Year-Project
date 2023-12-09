package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.Toast

@Suppress("DEPRECATION")
class Dashboard : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.my_dashboard)

        val topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animations)

        val dashboard1_id = findViewById<RelativeLayout>(R.id.dashboard1)
        val dashboard2_id = findViewById<RelativeLayout>(R.id.dashboard2)
        val dashboard3_id = findViewById<RelativeLayout>(R.id.dashboard3)
        val dashboard4_id = findViewById<RelativeLayout>(R.id.dashboard4)
        val dashboard5_id = findViewById<RelativeLayout>(R.id.dashboard5)

        dashboard1_id.animation = topAnimation
        dashboard2_id.animation = topAnimation
        dashboard3_id.animation = topAnimation
        dashboard4_id.animation = topAnimation
        dashboard5_id.animation = topAnimation

        dashboard1_id.setOnClickListener {
            val intent= Intent(this@Dashboard, Speech_Activity::class.java)
            startActivity(intent)
            finish()
        }

        dashboard2_id.setOnClickListener {
            val intent= Intent(this@Dashboard, Sound_Practice::class.java)
            startActivity(intent)
            finish()
        }

        dashboard3_id.setOnClickListener {
            val intent= Intent(this@Dashboard, Text_to_Speech::class.java)
            startActivity(intent)
            finish()
        }

        dashboard4_id.setOnClickListener {
            val intent= Intent(this@Dashboard, DatasetActivity::class.java)
            startActivity(intent)
            finish()
        }

        dashboard5_id.setOnClickListener {
            val intent= Intent(this@Dashboard, Speech_Therapist_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}