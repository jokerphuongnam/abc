package com.example.joker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast.*
import com.example.joker.Database.Data
import com.example.joker.ForgotPassword.ForgotPasswordActivity
import com.example.joker.MainSystem.AdminSystem.MainActivity
import com.example.joker.SignUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    private lateinit var forgotPasswordIntent : Intent
    private lateinit var signUpIntent: Intent
    private lateinit var mainIntent : Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Data.initialazed(this)
        this.forgotPasswordIntent = Intent(this.applicationContext, ForgotPasswordActivity::class.java)
        this.signUpIntent = Intent(this.applicationContext, SignUpActivity::class.java)
        this.mainIntent = Intent(this.applicationContext, MainActivity::class.java)
    }

    fun forgotPassAction(view: View) = this.startActivity(forgotPasswordIntent)

    @SuppressLint("ShowToast")
    fun signInAction(view: View) {
        if(labeUsername.text.toString() == "admin" && password.text.toString() == "admin"){
            this.startActivity(mainIntent)
        }
        else if(Data.db.login(username = labeUsername.text.toString(), password = password.text.toString())){
            makeText(this.applicationContext, "Login success", LENGTH_SHORT).show()
            mainIntent.putExtra("username", labeUsername.text.toString())
        }else{
            makeText(this.applicationContext, "Wrong username or password", LENGTH_SHORT).show()
        }
    }

    fun signUpAction(view: View) = this.startActivity(signUpIntent)
}
