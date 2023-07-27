package com.example.testhockey.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testhockey.R
import com.example.testhockey.business.modeles.SelfinstructionModel

class SelfInstructionAdapter() : RecyclerView.Adapter<SelfInstructionAdapter.SelfinstructionViewHolder>() {

    private val result = mutableListOf<SelfinstructionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelfinstructionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hockey_reference_guide, parent, false)

        return SelfinstructionViewHolder(view)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: SelfinstructionViewHolder, position: Int) {
        return holder.bind(result[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(result : List<SelfinstructionModel>){
        this.result.clear()
        this.result.addAll(result)
        notifyDataSetChanged()
    }

    class SelfinstructionViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val icon : ImageView = view.findViewById(R.id.iv_reference_guide)
        private val title : TextView = view.findViewById(R.id.tv_title)

        fun bind(result : SelfinstructionModel){
            title.text = result.title

            Glide.with(itemView.context)
                .load(result.icon)
                .override(107, 107)
                .into(icon)
        }

    }
}