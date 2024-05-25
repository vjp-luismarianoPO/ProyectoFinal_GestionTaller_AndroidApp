package com.example.gestiontaller.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiontaller.R
import com.example.gestiontaller.model.Accident
import com.example.gestiontaller.services.ApiClient
import com.example.gestiontaller.ui.viewholder.AccidentViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccidentAdapter(private val accidents: MutableList<Accident>): RecyclerView.Adapter<AccidentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccidentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_accident, parent, false)
        return AccidentViewHolder(itemView)
    }

    override fun getItemCount() = accidents.size

    //Called by RecyclerView to display the data at specified position
    override fun onBindViewHolder(holder: AccidentViewHolder, position: Int) {
        //Get the items at current position and bind the item to ViewHolder
        val accident = accidents[position]
        holder.render(accident)
        //Set up click listener for delete button
        holder.binding.deleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    //Call API to delete accident
                    val response = ApiClient.apiService.deleteAccident(accident.id)
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            //Remove the accident from list and notify the adapter
                            accidents.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, itemCount)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(holder.itemView.context, "Error al eliminar el siniestro", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(holder.itemView.context, "Error desconocido: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
