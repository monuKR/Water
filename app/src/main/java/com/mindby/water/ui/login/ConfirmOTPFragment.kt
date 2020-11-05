package com.mindby.water.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.mindby.water.R

class ConfirmOTPFragment : Fragment() {

    lateinit var loginViewModel: LoginViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_confirm_o_t_p, container, false)

        val otpEditText = view.findViewById<EditText>(R.id.otp)
        val confirmOTP = view.findViewById<Button>(R.id.confirm_OTP)

        otpEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                confirmOTP.isEnabled = s.length == 6
            }
        })


        confirmOTP.setOnClickListener {

            loginViewModel.createCredential(otpEditText.text.toString())
        }
        return view;
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConfirmOTPFragment()

    }
}