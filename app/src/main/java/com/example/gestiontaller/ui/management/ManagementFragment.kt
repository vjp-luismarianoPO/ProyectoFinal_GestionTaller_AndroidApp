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

class ManagementFragment : Fragment() {

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding!!
    private var _menuOptions: ChipNavigationBar? = null
    private var currentSection: String = "siniestros"
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: ManagementPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewPager = root.findViewById(R.id.view_pager)
        tabLayout = root.findViewById(R.id.tab_layout)

        adapter = ManagementPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "siniestros"
                1 -> "clientes"
                else -> null
            }
        }.attach()

        _menuOptions = root.findViewById(R.id.bottom_nav_var)
        _menuOptions?.setOnItemSelectedListener { id ->
            when (id) {
                R.id.nav_add -> {
                    loadLayoutForAction("add")
                }
                R.id.nav_get -> {
                    loadLayoutForAction("get")
                }
                R.id.nav_update -> {
                    loadLayoutForAction("update")
                }
                R.id.nav_delete -> {
                    loadLayoutForAction("delete")
                }
                else -> {
                    Toast.makeText(activity, "Opción desconocida", Toast.LENGTH_SHORT).show()
                }
            }
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                currentSection = when (tab.position) {
                    0 -> "siniestros"
                    1 -> "clientes"
                    else -> ""
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return root
    }

    private fun loadLayoutForAction(action: String) {
        adapter.setActionForSection(currentSection, action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
