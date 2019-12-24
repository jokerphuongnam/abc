package com.example.joker.ForgotPassword

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.joker.R
import kotlinx.android.synthetic.main.fragment_confirm_password.*
import org.apache.commons.lang3.RandomStringUtils.*

class ConfirmPassword : Fragment() {
    private lateinit var transaction: FragmentTransaction
    private lateinit var changePasswordForgot: ChangePasswordForgot
    private lateinit var listener : iUsername

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_password,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transaction = activity?.supportFragmentManager?.beginTransaction()!!
        changePasswordForgot = ChangePasswordForgot()
        username.text = arguments?.getString("username").toString()
        confirmCode.text = randomNumeric(4)
        enterConfirmCode.addTextChangedListener{
            if(it?.length!! == 4){
                Toast.makeText(context, if("$it" == with(confirmCode) {text.toString()})
                "Confirm success" else "Wrong confirm code", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    if(it.toString() == confirmCode.text.toString()){
                        listener.onUsernameFind(username.text.toString(), changePasswordForgot)
                        transaction.remove(this).add(R.id.mainForgotPass, changePasswordForgot).addToBackStack(null).commit()
                        it.clear()
                    }
                }, 500)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is iUsername){
            listener = context
        }
    }
}