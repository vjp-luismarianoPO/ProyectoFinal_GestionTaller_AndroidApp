package com.example.gestiontaller.ui.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiontaller.R
import com.example.gestiontaller.databinding.ItemAccidentBinding
import com.example.gestiontaller.model.Accident
import com.example.gestiontaller.ui.viewholder.AccidentViewHolder
import com.squareup.picasso.Picasso

class AccidentAdapter(private val accidents: List<Accident>): RecyclerView.Adapter<AccidentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccidentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_accident, parent, false)
        return AccidentViewHolder(itemView)
    }

    override fun getItemCount() = accidents.size

    override fun onBindViewHolder(holder: AccidentViewHolder, position: Int) {
        val accident = accidents[position]
        holder.render(accident)
    }

}
