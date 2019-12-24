package com.example.joker.Database

interface iData {
    fun login(username: String, password: String): Boolean
    fun findUsername(username: String) : Boolean
    fun createUsername(username: String, name: String, phoneNumber: String,password: String, sex: Boolean, birthYear: Int)
    fun takeInforData(username: String): Infor
}