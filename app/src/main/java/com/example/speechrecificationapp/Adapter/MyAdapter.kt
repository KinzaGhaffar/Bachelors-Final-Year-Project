package com.example.speechrecificationapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.speechrecificationapp.DataClass.User
import com.example.speechrecificationapp.R

class MyAdapter(private val userList:ArrayList<User>): RecyclerView.Adapter<MyAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int)
    {
        val currentitem = userList[position]

        holder.correctword.text = currentitem.correctword
        holder.incorrectword.text = currentitem.incorrectword
    }

    override fun getItemCount(): Int
    {
        return userList.size
    }
    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val correctword:TextView=itemView.findViewById(R.id.tvCorrect_Word)
        val incorrectword:TextView=itemView.findViewById(R.id.tvIncorrect_words)
    }

}