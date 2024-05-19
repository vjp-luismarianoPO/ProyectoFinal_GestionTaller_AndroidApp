package com.example.gestiontaller.ui.management

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ManagementPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var actionSiniestros: String = ""
    private var actionClientes: String = ""

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {



        return when (position) {
            0 -> {
                ManagementActionFragment.newInstance("siniestros", actionSiniestros)
            }
            1 -> {
                ManagementActionFragment.newInstance("clientes", actionClientes)
            }
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    fun setActionForSection(section: String, action: String) {
        when (section) {
            "siniestros" -> actionSiniestros = action
            "clientes" -> actionClientes = action
        }
    }
}
