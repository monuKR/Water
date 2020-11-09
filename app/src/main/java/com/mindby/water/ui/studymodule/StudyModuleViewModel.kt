package com.mindby.water.ui.studymodule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindby.water.model.Question

class StudyModuleViewModel : ViewModel() {

    val quiz : MutableList<Question> = mutableListOf()
    val question = MutableLiveData<Question>()
    val previousEnabled = MutableLiveData<Boolean>()
    val nextEnabled = MutableLiveData<Boolean>()
}