package com.example.speechrecificationapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.speechrecificationapp.*
import com.example.speechrecificationapp.uitel.loadImage
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity()
{
    internal var number:String?=""
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val details_back_id = findViewById<ImageView>(R.id.details_back)
        details_back_id.setOnClickListener{
            val intent= Intent(this@DetailsActivity, ItemsActivity::class.java)
            startActivity(intent)
            finish()
        }

        val about_id = findViewById<ImageView>(R.id.about)
        about_id.setOnClickListener{
            val intent= Intent(this@DetailsActivity, About::class.java)
            startActivity(intent)
            finish()
        }

        val logout_id = findViewById<ImageView>(R.id.logout)
        logout_id.setOnClickListener{
            val intent= Intent(this@DetailsActivity, second_screen::class.java)
            startActivity(intent)
            finish()
        }

        val call_id = findViewById<Button>(R.id.call)
        val phone_no_id = findViewById<EditText>(R.id.phone_no)
        call_id.setOnClickListener {
            number = phone_no_id.text.toString().trim()

            //dialer intent
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+Uri.encode(number)))
            startActivity(intent)
        }

        val intss = intent
        var nameT = intss.getStringExtra("NAMET")
        var desT = intss.getStringExtra("DESCRIT")
        var imgT = intss.getStringExtra("IMGURI")
        var contT = intss.getStringExtra("CONTACTT")
        var experT = intss.getStringExtra("EXPERIENCET")
        var timeT = intss.getStringExtra("TIMET")

        nameDetailTextView.text = nameT
        descriptionDetailTextView.text = desT
        teacherDetailImageView.loadImage(imgT)
        contactDetailTextView.text = contT
        ExperienceDetailTextView.text = experT
        TimeDetailTextView.text = timeT
    }
}