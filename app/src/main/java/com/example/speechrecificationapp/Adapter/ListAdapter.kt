package com.example.speechrecificationapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.speechrecificationapp.R
import com.example.speechrecificationapp.model.Therapist
import com.example.speechrecificationapp.uitel.loadImage
import com.example.speechrecificationapp.view.DetailsActivity

class ListAdapter(var mContext: Context, private val teacherList:ArrayList<Therapist>): RecyclerView.Adapter<ListAdapter.ListViewHolder>()
{
    inner class ListViewHolder(var v:View): RecyclerView.ViewHolder(v)
    {
        var imgT = v.findViewById<ImageView>(R.id.teacherImageView)
        var nameT = v.findViewById<TextView>(R.id.nameTextView)
        var descriT = v.findViewById<TextView>(R.id.descriptionTextView)
        var contactT = v.findViewById<TextView>(R.id.contactTextView)
        var experienceT = v.findViewById<TextView>(R.id.experienceTextView)
        var timeT = v.findViewById<TextView>(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder
    {
        var infalter = LayoutInflater.from(parent.context)
        var v = infalter.inflate(R.layout.row_item,parent,false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int =teacherList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int)
    {
        var newList = teacherList[position]
        holder.nameT.text = newList.name
        holder.descriT.text = newList.description
        holder.imgT.loadImage(newList.imageUrl)
        holder.contactT.text = (newList.contact)
        holder.experienceT.text = (newList.experiece)
        holder.timeT.text = (newList.time)

        holder.v.setOnClickListener {
            val name = newList.name
            val descrip = newList.description
            val imgUri = newList.imageUrl
            val phone = newList.contact
            val experienc = newList.experiece
            val timee = newList.time

            val mIntent = Intent(mContext, DetailsActivity::class.java)
            mIntent.putExtra("NAMET",name)
            mIntent.putExtra("DESCRIT",descrip)
            mIntent.putExtra("IMGURI",imgUri)
            mIntent.putExtra("CONTACTT",phone)
            mIntent.putExtra("EXPERIENCET",experienc)
            mIntent.putExtra("TIMET",timee)
            mContext.startActivity(mIntent)
        }
    }
}