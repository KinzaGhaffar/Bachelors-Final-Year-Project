package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.util.*

class Text_to_Speech : AppCompatActivity()
{
    lateinit var mTTs: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to__speech)

        mTTs = TextToSpeech(applicationContext,TextToSpeech.OnInitListener { status ->
            if(status!=TextToSpeech.ERROR){
                mTTs.language= Locale.UK
            }
        })

        val text_back_id = findViewById<ImageView>(R.id.text_back)
        text_back_id.setOnClickListener {
            val intent=Intent(this@Text_to_Speech, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val about_id = findViewById<ImageView>(R.id.about)
        about_id.setOnClickListener {
            val intent=Intent(this@Text_to_Speech, About::class.java)
            startActivity(intent)
            finish()
        }

        val logout_id = findViewById<ImageView>(R.id.logout)
        logout_id.setOnClickListener {
            val intent=Intent(this@Text_to_Speech, second_screen::class.java)
            startActivity(intent)
            finish()
        }

        val Text_to_speech_textview_id = findViewById<EditText>(R.id.Text_to_speech_textview)
        val text_to_speech_btn_id = findViewById<Button>(R.id.text_to_speech_btn)
        text_to_speech_btn_id.setOnClickListener {
            //get text from edittext
            val toSpeak = Text_to_speech_textview_id.text.toString()
            if(toSpeak=="")
            {
                //if there is no text in the text field
                Toast.makeText(this,"Enter Text", Toast.LENGTH_SHORT).show()
            }
            else
            {
                //if there is text in text field
                Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show()
                mTTs.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null)
            }
        }
    }
}