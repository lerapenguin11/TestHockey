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
import com.example.testhockey.business.modeles.ResultMatchesModel

class TodayMatchesAdapter() : RecyclerView.Adapter<TodayMatchesAdapter.TodayMatchesViewHolder>() {

    private val result = mutableListOf<ResultMatchesModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayMatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match_horizontal, parent, false)

        return TodayMatchesViewHolder(view)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: TodayMatchesViewHolder, position: Int) {
        return holder.bind(result[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(result : List<ResultMatchesModel>){
        this.result.clear()
        this.result.addAll(result)
        notifyDataSetChanged()
    }

    class TodayMatchesViewHolder (view : View) : RecyclerView.ViewHolder(view) {
        private val timeGame : TextView = view.findViewById(R.id.tv_time)
        private val resultGame : TextView = view.findViewById(R.id.tv_result)
        private val flagLeft : ImageView = view.findViewById(R.id.flag_left)
        private val flagRight : ImageView = view.findViewById(R.id.flag_right)
        private val countryLeft : TextView = view.findViewById(R.id.tv_country_left)
        private val countryRight : TextView = view.findViewById(R.id.tv_country_right)
        private val nameLeft : TextView = view.findViewById(R.id.tv_team_left)
        private val nameRight : TextView = view.findViewById(R.id.tv_team_right)

        fun bind(resultMatch : ResultMatchesModel){
            timeGame.text = resultMatch.time
            resultGame.text = resultMatch.score
            countryLeft.text = resultMatch.countryLeft
            countryRight.text = resultMatch.countryRight
            nameLeft.text = resultMatch.nameLeft
            nameRight.text = resultMatch.nameRight

            Glide.with(itemView.context)
                .load(resultMatch.flagLeft)
                .override(50, 50)
                .into(flagLeft)
            Glide.with(itemView.context)
                .load(resultMatch.flagRight)
                .override(50, 50)
                .into(flagRight)
        }
    }
}