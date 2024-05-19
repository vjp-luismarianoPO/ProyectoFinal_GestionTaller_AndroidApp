package com.example.gestiontaller.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestiontaller.R

class ManagementActionFragment : Fragment() {

    private var section: String? = null
    private var action: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            section = it.getString(ARG_SECTION)
            action = it.getString(ARG_ACTION)
        }
    }

    companion object {
        private const val ARG_SECTION = "section"
        private const val ARG_ACTION = "action"

        @JvmStatic
        fun newInstance(section: String, action: String) =
            ManagementActionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SECTION, section)
                    putString(ARG_ACTION, action)
                }
            }
    }
}
