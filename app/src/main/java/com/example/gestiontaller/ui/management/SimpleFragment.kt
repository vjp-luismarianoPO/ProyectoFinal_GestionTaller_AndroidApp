package com.example.gestiontaller.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SimpleFragment : Fragment() {

    companion object {
        private const val ARG_LAYOUT_ID = "layout_id"

        fun newInstance(layoutId: Int): SimpleFragment {
            val fragment = SimpleFragment()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_ID, layoutId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutId = arguments?.getInt(ARG_LAYOUT_ID) ?: 0
        return inflater.inflate(layoutId, container, false)
    }
}
