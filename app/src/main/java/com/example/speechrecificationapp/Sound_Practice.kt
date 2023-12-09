package com.example.speechrecificationapp

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.*
import java.lang.Exception
import java.util.*

@Suppress("DEPRECATION")
class Sound_Practice : AppCompatActivity(), View.OnClickListener
{
    private val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound__practice)

        val mic_id = findViewById<ImageView>(R.id.Mic)
        mic_id.setOnClickListener{
            speak();
        }

        val banana_id = findViewById<ImageView>(R.id.banana)
        val baseball_id = findViewById<ImageView>(R.id.baseball)
        val cellphone_id = findViewById<ImageView>(R.id.cellphone)
        val sailboat_id = findViewById<ImageView>(R.id.sailboat)
        val salad_id = findViewById<ImageView>(R.id.salad)
        val sing_id = findViewById<ImageView>(R.id.sing)
        val soap_id = findViewById<ImageView>(R.id.soap)
        val socks_id = findViewById<ImageView>(R.id.socks)
        val sofa_id = findViewById<ImageView>(R.id.sofa)
        val soup_id = findViewById<ImageView>(R.id.soup)
        val spoon_id = findViewById<ImageView>(R.id.spoon)
        val sun_id = findViewById<ImageView>(R.id.sun)
        val swimming_id = findViewById<ImageView>(R.id.swimming)
        val reading_id = findViewById<ImageView>(R.id.reading)
        val car_id = findViewById<ImageView>(R.id.car)
        val red_id = findViewById<ImageView>(R.id.red)
        val road_id = findViewById<ImageView>(R.id.road)

        val sound_practice_back_id = findViewById<ImageView>(R.id.sound_practice_back)
        sound_practice_back_id.setOnClickListener {
            val intent=Intent(this@Sound_Practice, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val about_id = findViewById<ImageView>(R.id.about)
        about_id.setOnClickListener {
            val intent=Intent(this@Sound_Practice, About::class.java)
            startActivity(intent)
            finish()
        }

        val logout_id = findViewById<ImageView>(R.id.logout)
        logout_id.setOnClickListener {
            val intent=Intent(this@Sound_Practice, second_screen::class.java)
            startActivity(intent)
            finish()
        }

        banana_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.banana)
            mediaPlayer?.start()
        }

        baseball_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.baseball)
            mediaPlayer?.start()
        }

        cellphone_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.cellphone)
            mediaPlayer?.start()
        }

        sailboat_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.sailboat)
            mediaPlayer?.start()
        }

        salad_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.salad)
            mediaPlayer?.start()
        }

        sing_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.sing)
            mediaPlayer?.start()
        }

        soap_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.soap)
            mediaPlayer?.start()
        }

        socks_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.socks)
            mediaPlayer?.start()
        }

        sofa_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.sofa)
            mediaPlayer?.start()
        }

        soup_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.soup)
            mediaPlayer?.start()
        }

        spoon_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.spoon)
            mediaPlayer?.start()
        }

        sun_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.sun)
            mediaPlayer?.start()
        }

        swimming_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.swimming)
            mediaPlayer?.start()
        }

        reading_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.reading)
            mediaPlayer?.start()
        }

        car_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.car)
            mediaPlayer?.start()
        }

        red_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.red)
            mediaPlayer?.start()
        }

        road_id.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.road)
            mediaPlayer?.start()
        }


        val PreviousBtn_id = findViewById<Button>(R.id.PreviousBtn)
        val NextBtn_id = findViewById<Button>(R.id.NextBtn)

        NextBtn_id.setOnClickListener(this)
        PreviousBtn_id.setOnClickListener(this)

    }

    override fun onClick(v: View?)
    {
        val viewflipper_id = findViewById<ViewFlipper>(R.id.viewflipper)
        val PreviousBtn_id = findViewById<Button>(R.id.PreviousBtn)
        val NextBtn_id = findViewById<Button>(R.id.NextBtn)

        if(v==NextBtn_id)
        {
            viewflipper_id.showNext()
        }
        else if(v==PreviousBtn_id)
        {
            viewflipper_id.showPrevious()
        }
    }

    //voice
    private fun speak()
    {
        val mIntent= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Something")
        try
        {
            startActivityForResult(mIntent,REQUEST_CODE_SPEECH_INPUT)
        }
        catch (e: Exception)
        {
            Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            REQUEST_CODE_SPEECH_INPUT->{
                if(resultCode== Activity.RESULT_OK && null!=data)
                {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val TextMicVoice_id=findViewById<TextView>(R.id.TextMicVoice)
                    TextMicVoice_id.text = result!![0]
                }
            }
        }
    }
}