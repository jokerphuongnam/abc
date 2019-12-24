package com.example.joker.MainSystem.AdminSystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.joker.Database.Data
import com.example.joker.R

class InforAdapter(val list : ArrayList<Data.DataList>) :
        Adapter<InforAdapter.InforViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InforViewHolder {
        return InforViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_view_admin, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: InforViewHolder, position: Int) {
        holder.username.text = list[position].username
        holder.password.text = list[position].password
    }

    class InforViewHolder(itemView: View) : ViewHolder(itemView){
        val  username = itemView.findViewById<TextView>(R.id.username)!!
        val password = itemView.findViewById<TextView>(R.id.password)!!
    }
}