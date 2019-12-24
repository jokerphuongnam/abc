@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package com.example.joker.SignUp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.joker.Database.Data
import com.example.joker.R
import kotlinx.android.synthetic.main.fragment_first_sign_up.*
import org.apache.commons.lang3.RandomStringUtils

class FirstSignUp : Fragment() {
    private lateinit var newUser: iNewUser
    private lateinit var nextStepFragment: Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_sign_up, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextStepFragment = ConfirmSignUp()
        viewConfirmCode.text = RandomStringUtils.randomNumeric(4)
        enterConfirmCode.addTextChangedListener {
            if(it?.length == 4){
                makeText(this.activity, if ("$it" == with(viewConfirmCode) {text.toString()})
                    "Confirm success" else "Wrong confirm code", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    if("$it" == with(viewConfirmCode) {text.toString()}){
                        confirmCode.visibility = View.INVISIBLE
                        term.visibility = View.VISIBLE
                    }
                }, 500)
            }
        }
        term.setOnClickListener {
            if(term.isChecked) {
                Handler().postDelayed({
                    signUpNext.visibility = View.VISIBLE
                    term.visibility = View.INVISIBLE
                }, 500)
            }
        }

        signUpNext.setOnClickListener {
            if (equalsZero(
                    data = arrayOf(
                        username.text.toString(), sex.text.toString(), phoneNumber.text.toString(),
                        password.text.toString(), birthYear.text.toString()
                    )
                ) && password.text.toString() == confirmPassword.text.toString()
                && !Data.db.findUsername(username = username.text.toString())){
                newUser.sendData(
                    username = username.text.toString(),
                    name = sex.text.toString(),
                    phoneNumber = phoneNumber.text.toString(),
                    password = password.text.toString(),
                    sex = male.isChecked,
                    birthYear = birthYear.text.toString().toInt(),
                    fragment = nextStepFragment
                )
                this.activity?.supportFragmentManager!!.beginTransaction().remove(this)
                    .add(R.id.sigUpMain, nextStepFragment).addToBackStack(null).commit()
                term.isChecked = false
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is iNewUser){
            newUser = context
        }
    }

    private fun equalsZero(data: Array<String>): Boolean{
        data.forEach {
            if(it.isEmpty())
                return false
        }
        return true
    }
}