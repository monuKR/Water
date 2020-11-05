package com.mindby.water.ui.welcome

import android.graphics.drawable.Drawable
import android.icu.text.MessagePattern
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mindby.water.R

const val ARG_LABEL = "text"

class WelcomeFragment() : Fragment() {


    companion object {
        fun newInstance(label:String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LABEL,label)
                }
            }
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}