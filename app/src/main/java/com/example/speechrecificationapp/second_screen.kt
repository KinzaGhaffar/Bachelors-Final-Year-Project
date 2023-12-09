package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button

@Suppress("DEPRECATION")
class second_screen : AppCompatActivity()
{
    private val SPLASH_TIME: Long = 3000
    val SPLASH_SCREEN = 5000

    private lateinit var second_screen_top_animation_id : Animation

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_second_screen)


        second_screen_top_animation_id = AnimationUtils.loadAnimation(this, R.anim.second_screen_top_animation)

//        val first = findViewById<View>(R.id.first_Line)
//        val second = findViewById<View>(R.id.second_Line)
//        val third = findViewById<View>(R.id.third_Line)
//        val fourth = findViewById<View>(R.id.fourth_Line)
//        val fifth = findViewById<View>(R.id.fifth_Line)
//        val sixth = findViewById<View>(R.id.sixth_Line)
//        val seventh = findViewById<View>(R.id.seventh_Line)
        val second_login_btn = findViewById<Button>(R.id.secondscreenloginbtn)
        val second_signup_btn = findViewById<Button>(R.id.secondscreensignupbtn)

//        first.animation = second_screen_top_animation_id
//        second.animation = second_screen_top_animation_id
//        third.animation = second_screen_top_animation_id
//        fourth.animation = second_screen_top_animation_id
//        fifth.animation = second_screen_top_animation_id
//        sixth.animation = second_screen_top_animation_id
//        seventh.animation = second_screen_top_animation_id
        second_login_btn.animation = second_screen_top_animation_id
        second_signup_btn.animation = second_screen_top_animation_id

        second_login_btn.setOnClickListener {
            val intent=Intent(this@second_screen ,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        second_signup_btn.setOnClickListener {
            val intent=Intent(this@second_screen ,Signup_Activity::class.java)
            startActivity(intent)
            finish()
        }


    }
}