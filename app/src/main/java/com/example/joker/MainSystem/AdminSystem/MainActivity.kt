package com.example.joker.MainSystem.AdminSystem

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.joker.Database.Data
import com.example.joker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doMainAdmin.layoutManager = LinearLayoutManager(this.applicationContext)
        doMainAdmin.adapter = InforAdapter(Data.list)

        Toast.makeText(applicationContext,"hello dev", Toast.LENGTH_LONG).show();
    }


}
