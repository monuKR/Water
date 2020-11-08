package com.mindby.water.ui.login

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mindby.water.R
import com.mindby.water.ui.LoginActivity

class ConfirmOTPFragment : Fragment() {

    lateinit var loginViewModel: LoginViewModel
    lateinit var resendOTP : Button
    lateinit var timer : TextView

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
         resendOTP = view.findViewById<Button>(R.id.resend_otp)
         timer = view.findViewById<TextView>(R.id.countdown_timer)

        otpEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                confirmOTP.isEnabled = s.length == 6
            }
        })

        // TODO() using count down properly
        startCountDown()


        confirmOTP.setOnClickListener {

            loginViewModel.createCredential(otpEditText.text.toString())
        }

        resendOTP.setOnClickListener {

            val activity: LoginActivity = activity as LoginActivity
            activity.send()
        }
        return view;
    }

    private fun startCountDown(){
        resendOTP.isEnabled = false
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "00 : " + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                resendOTP.isEnabled = true
            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConfirmOTPFragment()

    }
}