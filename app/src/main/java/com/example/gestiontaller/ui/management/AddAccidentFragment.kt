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
import androidx.core.text.set
import androidx.fragment.app.Fragment
import com.example.gestiontaller.R
import com.example.gestiontaller.databinding.FragmentAddAccidentBinding
import com.example.gestiontaller.model.Accident
import com.example.gestiontaller.model.AccidentDto
import com.example.gestiontaller.services.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit

class AddAccidentFragment : Fragment() {

    private var _binding: FragmentAddAccidentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddAccidentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinnerType: Spinner = root.findViewById(R.id.spinner)
        val spinnerCompanies: Spinner = root.findViewById(R.id.spinnerCompanies)
        val editTextClientId: EditText = root.findViewById(R.id.editTextIdClient)
        val editTextTotalAmount: EditText = root.findViewById(R.id.editTextTotalAmount)
        val editTextSupplierId: EditText = root.findViewById(R.id.editTextNumberIdSupplier)
        val confirmButton: ImageButton = root.findViewById(R.id.imageButtonConfirm)
        val clearButton: ImageButton = root.findViewById(R.id.imageButtonClear)

        //Configure the adapter for spinnerCompanies
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.companies,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCompanies.adapter = adapter
        }

        //Set click listener for confirm button
        confirmButton.setOnClickListener {
            val type: String = spinnerType.selectedItem.toString()
            val companyName: String = spinnerCompanies.selectedItem.toString()
            val idClient = editTextClientId.text.toString()
            val idSupplier = editTextSupplierId.text.toString()
            val totalAmount = editTextTotalAmount.text.toString()

            //Assign a number to idCompany based on companyName
            val idCompany = when (companyName) {
                "Mapfre" -> 1
                "Generali" -> 2
                "Allianz" -> 3
                "Asitur" -> 4
                "AXA" -> 5
                else -> 0 //Default value
            }

            //Prepare the AccidentDto object
            val accident = AccidentDto(
                Integer.parseInt(idClient),
                idCompany,
                Integer.parseInt(idSupplier),
                totalAmount.toDouble(),
                type
            )

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    //Make the HTTP request to insert the accident
                    val response: Response<Accident> = ApiClient.apiService.addAccident(accident)

                    //Check if the request was successful
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            showToast("Accidente insertado correctamente")
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            showToast("Error al insertar el accidente")
                        }
                    }
                } catch (e: Exception) {
                    Log.e("AddAccidentFragment", "Error: ${e.message}")
                }
            }
        }

        //Clear the edit text
        clearButton.setOnClickListener {
            editTextClientId.text.clear()
            editTextTotalAmount.text.clear()
            editTextSupplierId.text.clear()
        }

        return root
    }

    private suspend fun showToast(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
