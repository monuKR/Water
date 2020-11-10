package com.mindby.water.ui.main

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mindby.water.R


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    Options.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class Menu : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_options_dialog, container, false)

        return view
    }


    companion object {
        fun newInstance() = Menu()

    }
}