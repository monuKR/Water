package com.mindby.water.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mindby.water.R
import com.mindby.water.model.Module
import com.mindby.water.ui.studymodule.RecycleViewAdapter
import com.mindby.water.utils.Constants

class StudyModulesActivity : AppCompatActivity() {

    private val mFirestore = FirebaseFirestore.getInstance()
    private val mAuth = FirebaseAuth.getInstance()
    private val modules: MutableList<Module> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_modules)

        val recyclerView = findViewById<RecyclerView>(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecycleViewAdapter(this, modules)
        recyclerView.adapter = adapter



        mAuth.currentUser?.let { mFirestore.collection("users").document(it.uid) }

        mFirestore.collection(Constants.Firestore.MODULES).get().addOnCompleteListener {
            if(it.isSuccessful){
                for( document in it.result!!){
                    val id = document.id
                    val topic = document.getString(Constants.Firestore.TOPIC)
                    val attachments = document.data
                    attachments.remove(Constants.Firestore.TOPIC)

                    modules.add(Module(id, topic, attachments))

                }

                adapter.notifyDataSetChanged()
            }
        }.addOnFailureListener {

            Toast.makeText(this, "Problem Parsing data\nException: $it", Toast.LENGTH_SHORT).show()
        }



    }
}