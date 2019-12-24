package com.example.joker.Database

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson


class Data : iData{
    companion object{
        var db = Data()
        private var pref : SharedPreferences? = null
        private var gson = Gson()

        fun initialazed(activity: AppCompatActivity) {
            pref = activity.getSharedPreferences("com.teamtreehouse.colorsarefun.prefs", 0)
        }

        var list = ArrayList<DataList>()
    }

    @SuppressLint("DefaultLocale")
    override fun login(username: String, password: String): Boolean {
        val json = pref?.getString(username.toLowerCase(), "")
        if(json == "")
            return false
        if(gson.fromJson(json, Infor::class.java).password == password){
            return true
        }
        return false
    }

    @SuppressLint("DefaultLocale")
    override fun findUsername(username: String): Boolean {
        if(pref?.getString(username.toLowerCase(), "") == "")
            return false
        return false
    }

    override fun createUsername(
        username: String,
        name: String,
        phoneNumber: String,
        password: String,
        sex: Boolean,
        birthYear: Int
    ) {
        list.add(DataList(username, password))
        pref?.edit()?.putString(username, gson.toJson(Infor(name, phoneNumber, password, sex, birthYear)))?.apply()
    }

    override fun takeInforData(username: String): Infor {
        return gson.fromJson(pref?.getString(username, ""), Infor::class.java)
    }

    data class DataList(var username: String, var password: String)
}
