package com.example.joker.SignUp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.joker.Database.Data
import com.example.joker.Login
import com.example.joker.R
import kotlinx.android.synthetic.main.fragment_confirm_sign_up.*
import org.apache.commons.lang3.RandomStringUtils
import kotlin.properties.Delegates

class ConfirmSignUp : Fragment() {
    private lateinit var loginIntent: Intent
    private lateinit var ussernameString: String
    private lateinit var nameString: String
    private lateinit var phoneNumberString: String
    private lateinit var passswordString: String
    private var sexBoolean by Delegates.notNull<Boolean>()
    private var birthYearInt by Delegates.notNull<Int>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_sign_up,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ussernameString = arguments?.getString("username").toString()
        nameString =  arguments?.getString("name").toString()
        phoneNumberString = arguments?.getString("phoneNumber").toString()
        passswordString = arguments?.getString("password").toString()
        sexBoolean = arguments!!.getBoolean("sex")
        birthYearInt = arguments?.getInt("birthYear")!!
        loginIntent = Intent(context!!.applicationContext, Login::class.java)
        username.text = ussernameString
        password.text = passswordString
        name.text = nameString
        birthYear.text = birthYearInt.toString()
        phoneNumber.text = phoneNumberString
        sex.text = if(sexBoolean) "Male" else "Female"
        viewConfirm.text = RandomStringUtils.randomNumeric(4)
        enterConfirm.addTextChangedListener{
            if(it?.length == 4){
                if(it.toString() == viewConfirm.text.toString()){
                    Handler().postDelayed({
                        Data.db.createUsername(
                            username = ussernameString,
                            name = nameString,
                            phoneNumber = phoneNumberString,
                            password = passswordString,
                            sex = sexBoolean,
                            birthYear = birthYearInt)
                        Toast.makeText(context, "Confirm success", Toast.LENGTH_SHORT).show()
                    },500)
                    this.activity!!.startActivity(loginIntent)
                }else{
                    Toast.makeText(context, "Wrong confirm code", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}