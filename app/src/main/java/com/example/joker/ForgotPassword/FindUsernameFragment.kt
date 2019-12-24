package com.example.joker.ForgotPassword

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.joker.Database.Data
import com.example.joker.R
import kotlinx.android.synthetic.main.fragment_find_username.*

class FindUsernameFragment : Fragment() {
    private lateinit var confirmPassword: ConfirmPassword
    private lateinit var listener : iUsername

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_find_username, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmPassword = ConfirmPassword()
        nextStepForgotPassword.setOnClickListener{
            if(Data.db.findUsername(username = labeUsername.text.toString())){
                listener.onUsernameFind(labeUsername.text.toString().toLowerCase(), confirmPassword)
                activity?.supportFragmentManager?.beginTransaction()!!
                    .remove(this).add(R.id.mainForgotPass, confirmPassword).addToBackStack(null).commit()
            }else{
                Toast.makeText(context, "cann't find username", Toast.LENGTH_SHORT).show()
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