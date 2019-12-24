package com.example.joker.ForgotPassword

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.fragment.app.Fragment
import com.example.joker.Login
import com.example.joker.R
import kotlinx.android.synthetic.main.fragment_change_password_forgot.*

class ChangePasswordForgot : Fragment() {
    private lateinit var loginIntent: Intent
    private lateinit var usernameString: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_password_forgot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameString = arguments?.getString("username").toString()
        loginIntent = Intent(context!!.applicationContext, Login::class.java)
        changePassword.setOnClickListener{
            if(password.text.toString() == confirmPassword.text.toString()){

                this.activity?.applicationContext?.startActivity(loginIntent)
            }else{
                makeText(context, "Password and Confirm password are not same", LENGTH_SHORT).show()
            }
        }
    }
}