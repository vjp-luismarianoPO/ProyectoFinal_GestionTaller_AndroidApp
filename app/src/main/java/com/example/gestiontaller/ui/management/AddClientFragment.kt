package com.example.gestiontaller.ui.management

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gestiontaller.R
import com.example.gestiontaller.databinding.FragmentAddClientBinding
import com.example.gestiontaller.model.Client
import com.example.gestiontaller.model.ClientDto
import com.example.gestiontaller.services.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AddClientFragment : Fragment() {
    private var _binding: FragmentAddClientBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddClientBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinnerCompanies: Spinner = root.findViewById(R.id.spinnerCompanies)
        val editTextName: EditText = root.findViewById(R.id.editTextName)
        val editTextPhone: EditText = root.findViewById(R.id.editTextPhone)
        val editTextEmail: EditText = root.findViewById(R.id.editTextEmail)
        val editTextCarModel: EditText = root.findViewById(R.id.editTextCarModel)
        val editTextAccidentId: EditText = root.findViewById(R.id.editTextNumberIdAccident)
        val confirmButton: ImageButton = root.findViewById(R.id.imageButtonConfirm)
        val clearButton: ImageButton = root.findViewById(R.id.imageButtonClear)

        // Configure the adapter for spinnerCompanies
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.companies,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCompanies.adapter = adapter
        }

        confirmButton.setOnClickListener {
            val companyName: String = spinnerCompanies.selectedItem.toString()
            val clientName = editTextName.text.toString()
            val email = editTextEmail.text.toString()
            val phone = editTextPhone.text.toString()
            val carModel = editTextCarModel.text.toString()
            val accidentId = editTextAccidentId.text.toString()

            // Assign a number to idCompany based on companyName
            val idCompany = when (companyName) {
                "Mapfre" -> 1
                "Generali" -> 2
                "Allianz" -> 3
                "Asitur" -> 4
                "AXA" -> 5
                else -> 0 // Default value, or handle as needed
            }

            // Prepare the AccidentDto object
            val client = ClientDto(
                Integer.parseInt(accidentId),
                carModel,
                idCompany,
                email,
                clientName,
                phone
            )

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // Make the HTTP request to insert the accident
                    val response: Response<Client> = ApiClient.apiService.addClient(client)

                    // Check if the request was successful
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            showToast("Cliente insertado correctamente")
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            showToast("Error al insertar el cliente")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("AddClientFragment", "Error: ${e.message}")
                }
            }
        }

        clearButton.setOnClickListener {
            editTextAccidentId.text.clear()
            editTextName.text.clear()
            editTextPhone.text.clear()
            editTextAccidentId.text.clear()
            editTextEmail.text.clear()
            editTextCarModel.text.clear()
        }

        return root
    }

    private suspend fun showToast(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
        }
    }
}