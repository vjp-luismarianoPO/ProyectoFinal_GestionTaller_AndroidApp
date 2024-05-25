package com.example.gestiontaller.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiontaller.R
import com.example.gestiontaller.model.Client
import com.example.gestiontaller.services.ApiClient
import com.example.gestiontaller.ui.viewholder.ClientViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClientAdapter(private val clients: MutableList<Client>) :
    RecyclerView.Adapter<ClientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_client, parent, false)
        return ClientViewHolder(itemView)
    }

    override fun getItemCount() = clients.size

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]
        holder.render(client)
        holder.binding.deleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = ApiClient.apiService.deleteClient(client.id)
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            clients.removeAt(position)
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(position, itemCount)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(holder.itemView.context,"Error al eliminar el cliente",Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(holder.itemView.context,"Error desconocido: ${e.message}",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}