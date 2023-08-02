package com.example.testhockey.presentation.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testhockey.R
import com.example.testhockey.business.modeles.ResultMatchesModel
import com.example.testhockey.presentation.adapter.listener.PastMatchesListener
import com.example.testhockey.presentation.navFragment.HomeFragment
import com.example.testhockey.viewModel.CoinsViewModel

class PastMatchesAdapter(val pastMatchesListener: PastMatchesListener) : RecyclerView.Adapter<PastMatchesAdapter.PastMatchesViewHolder>() {

    private val result = mutableListOf<ResultMatchesModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastMatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_past_matches, parent, false)

        return PastMatchesViewHolder(view)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: PastMatchesViewHolder, position: Int) {
        val resultMatch : ResultMatchesModel = result[position]
        var open = false
        holder.itemView.setOnClickListener {
            if (open == false){
                pastMatchesListener.pastMatches(result = resultMatch)
                holder.container.visibility = View.GONE
                open = true
            }
        }
        holder.timeGame.text = resultMatch.time
        holder.nameLeft.text = resultMatch.nameLeft
        holder.nameRight.text = resultMatch.nameRight
        holder.gameData.text = resultMatch.date

        Glide.with(holder.itemView.context)
            .load(resultMatch.flagLeft)
            .override(50, 50)
            .into(holder.flagLeft)
        Glide.with(holder.itemView.context)
            .load(resultMatch.flagRight)
            .override(50, 50)
            .into(holder.flagRight)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(result : List<ResultMatchesModel>){
        this.result.clear()
        this.result.addAll(result)
        notifyDataSetChanged()
    }

    class PastMatchesViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val nameLeft : TextView = view.findViewById(R.id.tv_name_team_left)
        val nameRight : TextView = view.findViewById(R.id.tv_name_team_right)
        val flagLeft : ImageView = view.findViewById(R.id.iv_flag_left)
        val flagRight : ImageView = view.findViewById(R.id.iv_flag_right)
        val timeGame : TextView = view.findViewById(R.id.time)
        val gameData : TextView = view.findViewById(R.id.tv_data)
        val container : ConstraintLayout = view.findViewById(R.id.bg_container)
    }
}