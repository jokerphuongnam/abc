package com.example.joker.ForgotPassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.joker.R
import com.example.joker.R.layout.activity_forgot_password

class ForgotPasswordActivity : AppCompatActivity(), iUsername {
    override fun onUsernameFind(data: String, frament: Fragment)  {
        frament.arguments = Bundle()
        frament.arguments!!.putString("username", data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_forgot_password)
        supportFragmentManager.beginTransaction().replace(R.id.mainForgotPass, FindUsernameFragment()).commit()
    }
}