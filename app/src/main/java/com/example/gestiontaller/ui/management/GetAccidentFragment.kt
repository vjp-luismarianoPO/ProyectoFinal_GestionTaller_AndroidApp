package com.example.gestiontaller.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiontaller.databinding.FragmentGetAccidentBinding
import com.example.gestiontaller.model.Accident
import com.example.gestiontaller.services.ApiClient
import com.example.gestiontaller.ui.adapter.AccidentAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetAccidentFragment : Fragment() {
    private var _binding: FragmentGetAccidentBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var accidentAdapter: AccidentAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getAccidents() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<ArrayList<Accident>> = ApiClient.apiService.getAccidents()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        accidentAdapter = AccidentAdapter(response.body()!!.toMutableList())
                        recyclerView.adapter = accidentAdapter
                    }
                } else {
                    showToast("Error al obtener los siniestros")
                }
            } catch (e: Exception) {
                showToast(e.message ?: "Error desconocido")
            }
        }
    }

    private suspend fun showToast(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetAccidentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerViewClients
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getAccidents()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
