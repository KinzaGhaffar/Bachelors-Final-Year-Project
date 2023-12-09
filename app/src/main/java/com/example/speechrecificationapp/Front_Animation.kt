package com.example.speechrecificationapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.speechrectificationapp.MainActivity

@Suppress("DEPRECATION")
class Front_Animation : AppCompatActivity()
{
    private val SPLASH_TIME: Long = 5500
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_front__animation)

        Handler().postDelayed({
            startActivity(Intent(this, second_screen::class.java))
            finish()
        }, SPLASH_TIME)
    }
}