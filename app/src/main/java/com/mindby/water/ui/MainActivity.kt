package com.mindby.water.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mindby.water.R

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()

        mAuth.addAuthStateListener(mAuthStateListener)

    }

    override fun onBackPressed() {
        super.onBackPressed()

        mAuth.removeAuthStateListener(mAuthStateListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    private val mAuthStateListener = FirebaseAuth.AuthStateListener {
        val user = it.currentUser

       if (user != null){
           updateUI(user)
       }else{
           login()
       }

    }

    private fun login(){
        startActivity(Intent(this, LoginActivity::class.java))
    }



    private fun updateUI(user: FirebaseUser){

        val userID = findViewById<TextView>(R.id.user_id)
        Log.e("TAGG","userId${user.uid}")
        userID.text = user.uid
    }

    fun modules(view: View) {}
}