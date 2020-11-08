package com.mindby.water.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mindby.water.R
import com.mindby.water.ui.LoginActivity

class SendOTPFragment : Fragment() {

    private lateinit var loginViewModel:LoginViewModel

    companion object {
        @JvmStatic
        fun newInstance() = SendOTPFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_send_o_t_p, container, false)

        val phoneEditText  = view.findViewById<EditText>(R.id.phone_number)
        val sendOTP = view.findViewById<Button>(R.id.send_OTP)

        phoneEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                sendOTP.isEnabled = s.length == 10

            }

        })

        sendOTP.setOnClickListener{
            loginViewModel.phone.value = phoneEditText.text.toString()

            val activity: LoginActivity = activity as LoginActivity
            activity.send(phoneEditText.text.toString())
        }

        return view;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

    }



}