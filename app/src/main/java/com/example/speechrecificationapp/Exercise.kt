package com.example.speechrecificationapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

class Exercise : AppCompatActivity()
{
    var videoview :VideoView? =null
    var mediaController : MediaController?=null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        videoview = findViewById(R.id.video) as VideoView

        if(mediaController==null)
        {
            mediaController= MediaController(this)
            mediaController!!.setAnchorView(this.videoview)
        }
        videoview!!.setMediaController(mediaController)
        videoview!!.setVideoURI(Uri.parse("android.resources://"+packageName+"/"+R.raw.exer))
        videoview!!.requestFocus()
        videoview!!.start()
        videoview!!.setOnCompletionListener {
            Toast.makeText(applicationContext,"Video End",Toast.LENGTH_SHORT).show()
        }
        videoview!!.setOnErrorListener { mediaPlayer, i, i2 ->
            Toast.makeText(applicationContext,"Video End",Toast.LENGTH_SHORT).show()
            false
        }
    }
}