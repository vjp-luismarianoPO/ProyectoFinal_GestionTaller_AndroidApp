package com.example.gestiontaller.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiontaller.databinding.ItemAccidentBinding
import com.example.gestiontaller.model.Accident

class AccidentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemAccidentBinding.bind(itemView)

    fun render(accidentModel: Accident) {
        binding.textViewAccidentId.text = accidentModel.id.toString()
        binding.textViewAccidentType.text = accidentModel.type
        binding.textViewAccidentDate.text = accidentModel.date
        binding.textViewAccidentTotalAmount.text = accidentModel.totalAmount.toString()
    }
}