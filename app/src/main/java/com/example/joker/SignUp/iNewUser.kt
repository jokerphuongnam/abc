package com.example.joker.SignUp

import androidx.fragment.app.Fragment

interface iNewUser {
    fun sendData(username: String, name: String, phoneNumber: String,password: String, sex: Boolean, birthYear: Int, fragment: Fragment)
}