package com.example.gestiontaller.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.gestiontaller.R
import com.example.gestiontaller.databinding.FragmentManagementBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import retrofit2.http.GET

class ManagementFragment : Fragment() {

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding!!
    private var _menuOptions: ChipNavigationBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _menuOptions = root.findViewById(R.id.bottom_nav_var)
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
                R.id.nav_delete -> {
                    openFragment(DeleteAccidentFragment())
                }
                else -> {
                    // Maneja casos inesperados si es necesario
                }
            }
        }

        // Selecciona la primera opción por defecto
        _menuOptions?.setItemSelected(R.id.nav_add, true)

        return root
    }

    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
