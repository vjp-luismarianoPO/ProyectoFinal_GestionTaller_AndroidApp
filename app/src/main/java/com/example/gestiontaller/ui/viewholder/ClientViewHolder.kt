package com.example.gestiontaller.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiontaller.databinding.ItemAccidentBinding
import com.example.gestiontaller.databinding.ItemClientBinding
import com.example.gestiontaller.model.Accident
import com.example.gestiontaller.model.Client

class ClientViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemClientBinding.bind(itemView)

    fun render(clientModel: Client) {
        binding.textViewEmail.text = clientModel.email
        binding.textViewName.text = clientModel.name
        binding.textViewPhone.text = clientModel.phone
        binding.textViewcarModel.text = clientModel.carModel
    }
}