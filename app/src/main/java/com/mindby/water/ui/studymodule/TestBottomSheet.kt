package com.mindby.water.ui.studymodule

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mindby.water.R
import com.mindby.water.model.Question
import com.mindby.water.ui.login.LoginViewModel

class TestBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = TestBottomSheet()
    }

    lateinit var viewModel: StudyModuleViewModel

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity()).get(StudyModuleViewModel::class.java)
//
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.layout_test, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(StudyModuleViewModel::class.java)

        val question = view.findViewById<TextView>(R.id.question)
        val option1 = view.findViewById<TextView>(R.id.option1)
        val option2 = view.findViewById<TextView>(R.id.option2)
        val option3 = view.findViewById<TextView>(R.id.option3)
        val option4 = view.findViewById<TextView>(R.id.option4)
        val questionNumber = view.findViewById<TextView>(R.id.question_number)

        val previous = view.findViewById<Button>(R.id.previous)

        viewModel.question.observe(viewLifecycleOwner){

            question.text = it.question
            option1.text = it.option1
            option2.text = it.option2
            option3.text = it.option3
            option4.text = it.option4


            questionNumber.text = it.questionNumber

        }



        return view
    }



}