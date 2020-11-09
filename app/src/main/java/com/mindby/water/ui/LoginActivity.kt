package com.mindby.water.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.mindby.water.R
import com.mindby.water.ui.login.ConfirmOTPFragment
import com.mindby.water.ui.login.LoginViewModel
import com.mindby.water.ui.login.SendOTPFragment
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    lateinit var loginViewModel: LoginViewModel

    lateinit var sendOTPFragment: SendOTPFragment
    lateinit var confirmOTPFragment: ConfirmOTPFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        val languageToLoad = "hi" // your language
//
//        val locale = Locale(languageToLoad)
//        Locale.setDefault(locale)
//        val config = Configuration()
//        config.locale = locale
//        baseContext.resources.updateConfiguration(
//            config,
//            baseContext.resources.displayMetrics
//        )

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        sendOTPFragment = SendOTPFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, sendOTPFragment).commit()

        loginViewModel.credential.observe(this,
            {
                signInWithPhoneAuthCredential(it)
            }
        )

    }


    fun send(phone: String? = null) {

        phone ?: loginViewModel.phone.value

        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+91$phone")
            .setTimeout(5L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)


    }


    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)
                Log.e(ContentValues.TAG, "onCodeSent:$verificationId")

                loginViewModel.storedVerificationId.value = verificationId
                loginViewModel.resendToken.value = token

                confirmOTPFragment = ConfirmOTPFragment.newInstance()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, confirmOTPFragment).commit()

//                confirmOTPFragment.startCountDown()

            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {


            }

            override fun onVerificationFailed(exception: FirebaseException) {
            }
        }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(ContentValues.TAG, "signInWithCredential:success")

                    finish()

                } else {
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)

                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid

                        confirmOTPFragment.otpEditText.error = "Incorrect OTP"

                        Log.e("TAGG", "invalid verification code")

                    }
                }
            }
    }
}