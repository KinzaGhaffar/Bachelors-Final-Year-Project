package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.speechrecificationapp.Class.DatabaseModel
import com.google.firebase.database.*

class DatasetActivity : AppCompatActivity()
{
        private lateinit var reference: DatabaseReference
        private lateinit var database :FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dataset)

        val savewordsbtn_id = findViewById<Button>(R.id.savewordsbtn)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Words")

        savewordsbtn_id.setOnClickListener {
            sendData()
        }

        val dataset_back_id=findViewById<ImageView>(R.id.dataset_back)
        dataset_back_id.setOnClickListener{
            val intent= Intent(this@DatasetActivity, Dashboard::class.java)
            startActivity(intent)
            finish()
        }

        val about_id=findViewById<ImageView>(R.id.about)
        about_id.setOnClickListener{
            val intent= Intent(this@DatasetActivity, About::class.java)
            startActivity(intent)
            finish()
        }

        val logout_id=findViewById<ImageView>(R.id.logout)
        logout_id.setOnClickListener{
            val intent= Intent(this@DatasetActivity, second_screen::class.java)
            startActivity(intent)
            finish()
        }

        val showbtn_id=findViewById<Button>(R.id.showbtn)
        showbtn_id.setOnClickListener{
            val intent= Intent(this@DatasetActivity, UserlistActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun sendData()
    {
        val correctwords = findViewById<EditText>(R.id.Correct_Word)
        val incorrectwords = findViewById<EditText>(R.id.Incorrect_words)

        var correctwords_id = correctwords.text.toString().trim()
        var incorrectwords_id = incorrectwords.text.toString().trim()

        if(correctwords_id.isNotEmpty() && incorrectwords_id.isNotEmpty())
        {
            var model = DatabaseModel(correctwords_id, incorrectwords_id)
            var id = reference.push().key

            //here we send the data to firebase
            reference.child(id!!).setValue(model)
            correctwords.setText("")
            incorrectwords.setText("")
        }
        else
        {
            Toast.makeText(this,"Both Fields required!",Toast.LENGTH_SHORT).show()
        }
    }
}