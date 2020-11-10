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
import com.mindby.water.ui.main.Menu
import com.mindby.water.utils.MySharedPreferences

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()


    }

    override fun onBackPressed() {
        mAuth.removeAuthStateListener(mAuthStateListener)

        super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth.addAuthStateListener(mAuthStateListener)



    }

    private val mAuthStateListener = FirebaseAuth.AuthStateListener {
        val user = it.currentUser


       if (user != null){
           updateUI(user)
       }else{
           login()
           welcomeCheck()

       }

    }

    private fun welcomeCheck(){
        val sharedPreferences = this.getSharedPreferences(MySharedPreferences.WELCOME,0)

        val isFirstTime = sharedPreferences.getBoolean(MySharedPreferences.FIRST_TIME,true)

        if (isFirstTime){
            startActivity(Intent(this,WelcomeActivity::class.java))
        }

    }

    private fun login(){
        startActivity(Intent(this, LoginActivity::class.java))
    }



    private fun updateUI(user: FirebaseUser){
        setContentView(R.layout.activity_main)

        val userID = findViewById<TextView>(R.id.user_id)
        Log.e("TAGG","userId${user.uid}")

        userID.text = user.uid
    }

    fun modules(view: View) {
        startActivity(Intent(this,StudyModulesActivity::class.java))
    }

    fun openMenu(view: View) {
        Menu.newInstance().show(supportFragmentManager,"dialog")
    }
}