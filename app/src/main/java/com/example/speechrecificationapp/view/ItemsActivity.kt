package com.example.speechrecificationapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speechrecificationapp.*
import com.example.speechrecificationapp.Adapter.ListAdapter
import com.example.speechrecificationapp.R
import com.example.speechrecificationapp.model.Therapist
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : AppCompatActivity()
{
        private var mStorage: FirebaseStorage? = null
        private var mDatabaseRef: DatabaseReference? = null
        private var mDBListener: ValueEventListener? = null
        private lateinit var mTeachers:ArrayList<Therapist>
        private lateinit var listAdapter: ListAdapter
        override fun onCreate(savedInstanceState: Bundle?)

    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

//        val therapist_items_back_id=findViewById<ImageView>(R.id.therapist_items_back)
//        therapist_items_back_id.setOnClickListener{
//            val intent= Intent(this@ItemsActivity, Speech_Therapist_Activity::class.java)
//            startActivity(intent)
//            finish()
//        }
//
//        val about_id=findViewById<ImageView>(R.id.about)
//        about_id.setOnClickListener{
//            val intent= Intent(this@ItemsActivity, About::class.java)
//            startActivity(intent)
//            finish()
//        }
//
//        val logout_id=findViewById<ImageView>(R.id.logout)
//        logout_id.setOnClickListener{
//            val intent= Intent(this@ItemsActivity, second_screen::class.java)
//            startActivity(intent)
//            finish()
//        }

        /**set adapter*/
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this@ItemsActivity)
        myDataLoaderProgressBar.visibility = View.VISIBLE
        mTeachers = ArrayList()
        listAdapter = ListAdapter(this@ItemsActivity,mTeachers)
        mRecyclerView.adapter = listAdapter

        /**set Firebase Database*/
        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Therapist")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(error: DatabaseError)
            {
                Toast.makeText(this@ItemsActivity,error.message, Toast.LENGTH_SHORT).show()
                myDataLoaderProgressBar.visibility = View.INVISIBLE
            }

            override fun onDataChange(snapshot: DataSnapshot)
            {
                mTeachers.clear()
                for (teacherSnapshot in snapshot.children)
                {
                    val upload = teacherSnapshot.getValue(Therapist::class.java)
                    upload!!.key = teacherSnapshot.key
                    mTeachers.add(upload)
                }

                listAdapter.notifyDataSetChanged()
                myDataLoaderProgressBar.visibility = View.GONE
            }
        })
    }

    override fun onDestroy()
    {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }
}