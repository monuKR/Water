package com.mindby.water.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.*

class LoginViewModel() : ViewModel() {


    var phone = MutableLiveData<String>()
    var resendCountDownStart = MutableLiveData<Boolean>()
    val storedVerificationId = MutableLiveData<String>()
    val resendToken = MutableLiveData<PhoneAuthProvider.ForceResendingToken>()
    val credential = MutableLiveData<PhoneAuthCredential>()

    public fun createCredential(otp : String) {


        credential.postValue(
            PhoneAuthProvider.getCredential(
                storedVerificationId.value!!,
                otp
            )
        )
    }


}