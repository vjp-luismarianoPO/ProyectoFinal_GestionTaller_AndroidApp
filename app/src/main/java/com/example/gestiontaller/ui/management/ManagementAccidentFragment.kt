package com.example.gestiontaller.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gestiontaller.R
import com.example.gestiontaller.databinding.FragmentManagementAccidentBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class ManagementAccidentFragment : Fragment() {

    private var _binding: FragmentManagementAccidentBinding? = null
    private val binding get() = _binding!!
    private var _menuOptions: ChipNavigationBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagementAccidentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _menuOptions = root.findViewById(R.id.bottom_nav_var_accidents)
        _menuOptions?.setOnItemSelectedListener { id ->
            when (id) {
                R.id.nav_add -> {
                    openFragment(AddAccidentFragment())
                }

                R.id.nav_get -> {
                    openFragment(GetAccidentFragment())
                }

                R.id.nav_update -> {
                    openFragment(UpdateAccidentFragment())
                }

                else -> {}
            }
        }

        // Select first option default
        _menuOptions?.setItemSelected(R.id.nav_get, true)
        return root
    }

    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_accidents, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
