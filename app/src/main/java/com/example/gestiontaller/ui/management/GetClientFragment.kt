package com.example.gestiontaller.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiontaller.databinding.FragmentGetClientBinding
import com.example.gestiontaller.model.Client
import com.example.gestiontaller.services.ApiClient
import com.example.gestiontaller.ui.adapter.ClientAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetClientFragment : Fragment() {
    private var _binding: FragmentGetClientBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdapter: ClientAdapter

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getClients() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<ArrayList<Client>> = ApiClient.apiService.getClients()
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        clientAdapter = ClientAdapter(response.body()!!.toMutableList())
                        recyclerView.adapter = clientAdapter
                    }
                } else {
                    showToast("Error al obtener los clientes")
                }
            } catch (e: Exception) {
                showToast(e.message ?: "Error desconocido")
            }
        }
    }

    private suspend fun showToast(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGetClientBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerViewClients
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        getClients()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}