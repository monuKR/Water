package com.mindby.water.ui.studymodule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.mindby.water.R
import com.mindby.water.model.Module
import com.mindby.water.model.Question
import com.mindby.water.utils.Constants


class RecycleViewAdapter(var context: Context, var modules: List<Module>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val questions : MutableList<Question> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_module, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val test = holder.itemView.findViewById<Button>(R.id.test)
        val moduleNumber = holder.itemView.findViewById<TextView>(R.id.module_number)
        val topicName = holder.itemView.findViewById<TextView>(R.id.topic_name)

        val attachedFile = holder.itemView.findViewById<LinearLayout>(R.id.attached_file)
        val attachedLecture = holder.itemView.findViewById<LinearLayout>(R.id.attached_lecture)

        val MODULE_NUMBER = modules[position].id

        moduleNumber.text = "Module: + $MODULE_NUMBER"

        topicName.text = modules[position].topic

        attachedFile.setOnClickListener {

        }

        attachedLecture.setOnClickListener {

        }

        test.setOnClickListener {
            val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager

            TestBottomSheet.newInstance()
                .show(manager, "dialog")

            getQuestions(MODULE_NUMBER)
        }


    }

    override fun getItemCount(): Int {
        return this.modules.size
    }

    private fun getQuestions(moduleNumber: String) {

        val mFirestore = FirebaseFirestore.getInstance()

        mFirestore.collection(Constants.Firestore.MODULES).document(moduleNumber)
            .collection(Constants.Firestore.QUIZ).get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (quiz in it.result!!) {
                    val questionNumber = quiz.id
                    val question = quiz.getString(Constants.Firestore.QUESTION)
                    val option1 = quiz.getString("1")
                    val option2 = quiz.getString("2")
                    val option3 = quiz.getString("3")
                    val option4 = quiz.getString("4")
                    val answer = quiz.getString(Constants.Firestore.ANSWER)
                    questions.add(
                        Question(
                            questionNumber,
                            question,
                            option1,
                            option2,
                            option3,
                            option4,
                            answer
                        )
                    )
                }


            }
        }
    }

}