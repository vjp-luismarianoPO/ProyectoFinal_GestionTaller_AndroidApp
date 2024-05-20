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
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class ManagementFragment : Fragment() {

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding!!
    private var _menuOptions: ChipNavigationBar? = null
    private var currentSection: String? = null

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var tabItemAccidents: TabItem
    private lateinit var tabItemClients: TabItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewPager = root.findViewById(R.id.view_pager)
        tabLayout = root.findViewById(R.id.tab_layout)

        //tabItemAccidents = root.findViewById(R.id.tab_item_accidents)
        //tabItemClients = root.findViewById(R.id.tab_item_clients)


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

        return root
    }

    private fun loadLayoutForAction(action: String) {
        val layoutId = when (currentSection) {
            "siniestros" -> when (action) {
                "add" -> R.layout.fragment_add_accident
                "get" -> R.layout.fragment_get_accident
                "update" -> R.layout.fragment_update_accident
                "delete" -> R.layout.fragment_delete_accident
                else -> null
            }

            "clientes" -> when (action) {
                "add" -> R.layout.fragment_add_accident
                "get" -> R.layout.fragment_get_client
                "update" -> R.layout.fragment_update_client
                "delete" -> R.layout.fragment_delete_client
                else -> null
            }

            else -> null
        }

        if (layoutId != null) {
            viewPager.adapter = SimpleFragmentPagerAdapter(layoutId, this)
        } else {
            Toast.makeText(activity, "Error al cargar el layout", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
