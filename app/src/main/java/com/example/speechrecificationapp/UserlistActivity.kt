package com.example.speechrecificationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.speechrecificationapp.Adapter.MyAdapter
import com.example.speechrecificationapp.DataClass.User
import com.google.firebase.database.*

class UserlistActivity : AppCompatActivity()
{
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userlist)

        userRecyclerView = findViewById(R.id.userlist)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()

        val show_dataset_back_id=findViewById<ImageView>(R.id.show_dataset_back)
        show_dataset_back_id.setOnClickListener{
            val intent= Intent(this@UserlistActivity, DatasetActivity::class.java)
            startActivity(intent)
            finish()
        }

        val about_id=findViewById<ImageView>(R.id.about)
        about_id.setOnClickListener{
            val intent= Intent(this@UserlistActivity, About::class.java)
            startActivity(intent)
            finish()
        }

        val logout_id=findViewById<ImageView>(R.id.logout)
        logout_id.setOnClickListener{
            val intent= Intent(this@UserlistActivity, second_screen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getUserData()
    {
        dbref = FirebaseDatabase.getInstance().getReference("Words")

        dbref.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot)
            {
                if(snapshot.exists())
                {
                    for(userSnapshot in snapshot.children)
                    {
                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter=MyAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError)
            {
                TODO("Not yet implemented")
            }

        })
    }
}