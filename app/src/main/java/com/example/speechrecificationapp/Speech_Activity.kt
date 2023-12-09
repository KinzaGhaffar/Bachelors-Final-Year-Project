package com.example.speechrecificationapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.android.synthetic.main.activity_speech_.*
import java.lang.Exception
import java.util.*

@Suppress("DEPRECATION")
class Speech_Activity : AppCompatActivity()
{
    lateinit var mTTs: TextToSpeech
    private val REQUEST_CODE_SPEECH_INPUT = 100

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_)

        mTTs = TextToSpeech(applicationContext,TextToSpeech.OnInitListener { status ->
            if(status!=TextToSpeech.ERROR)
            {
                mTTs.language= Locale.UK
            }
        })

        val speech_back_id = findViewById<ImageView>(R.id.speech_back)
        speech_back_id.setOnClickListener {
            val intent=Intent(this@Speech_Activity, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val about_id = findViewById<ImageView>(R.id.about)
        about_id.setOnClickListener {
            val intent=Intent(this@Speech_Activity, About::class.java)
            startActivity(intent)
            finish()
        }

        val exit_id = findViewById<ImageView>(R.id.logout)
        exit_id.setOnClickListener {
            val intent=Intent(this@Speech_Activity, second_screen::class.java)
            startActivity(intent)
            finish()
        }

        val TextVoice_id = findViewById<EditText>(R.id.TextVoice)
        val text_to_speech_btn_id = findViewById<Button>(R.id.text_to_speech_btn)
        text_to_speech_btn_id.setOnClickListener {
            //get text from edittext
            val toSpeak = TextVoice_id.text.toString()
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

//        initPython()
        val voice_id = findViewById<ImageView>(R.id.voice)
        voice_id.setOnClickListener{
            speak();
        }
    }

//    private fun initPython()
//    {
//        if (!Python.isStarted())
//        {
//            Python.start(AndroidPlatform(this))
//        }
//    }

    private fun speak()
    {
        val mIntent=Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        mIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
        mIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Something")

        try
        {
            startActivityForResult(mIntent,REQUEST_CODE_SPEECH_INPUT)
        }
        catch (e:Exception)
        {
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
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
                    val textvoice_id=findViewById<TextView>(R.id.TextVoice)
                    textvoice_id.text = result!![0]
                }
            }

        }
    }

}