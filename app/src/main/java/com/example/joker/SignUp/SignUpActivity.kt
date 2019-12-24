package com.example.joker.SignUp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.joker.R.*
import com.example.joker.R.id.*

class SignUpActivity : AppCompatActivity(), iNewUser {
    override fun sendData(
        username: String,
        name: String,
        phoneNumber: String,
        password: String,
        sex: Boolean,
        birthYear: Int,
        fragment: Fragment
    ) {
        fragment.arguments = Bundle()
        fragment.arguments?.putString("username",username)
        fragment.arguments?.putString("name",name)
        fragment.arguments?.putString("phoneNumber",phoneNumber)
        fragment.arguments?.putString("password",password)
        fragment.arguments?.putBoolean("sex",sex)
        fragment.arguments?.putInt("birthYear",birthYear)
        /*
        * var bundle = Bundle()
        bundle.putString("username",username)
        bundle.putString("name",name)
        bundle.putString("phoneNumber",phoneNumber)
        bundle.putString("password",password)
        bundle.putBoolean("sex",sex)
        bundle.putInt("birthYear",birthYear)
        fragment.arguments = bundle
        * */
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_sign_up)
        supportFragmentManager.beginTransaction().replace(sigUpMain, FirstSignUp()).commit()
    }
}