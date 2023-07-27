package com.example.testhockey.presentation.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testhockey.R
import com.example.testhockey.business.modeles.ResultMatchesModel

class PastMatchesAdapter : RecyclerView.Adapter<PastMatchesAdapter.PastMatchesViewHolder>() {

    private val result = mutableListOf<ResultMatchesModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastMatchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_past_matches, parent, false)

        return PastMatchesViewHolder(view)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: PastMatchesViewHolder, position: Int) {
        return holder.bind(result[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(result : List<ResultMatchesModel>){
        this.result.clear()
        this.result.addAll(result)
        notifyDataSetChanged()
    }

    class PastMatchesViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val nameLeft : TextView = view.findViewById(R.id.tv_name_team_left)
        private val nameRight : TextView = view.findViewById(R.id.tv_name_team_right)
        private val flagLeft : ImageView = view.findViewById(R.id.iv_flag_left)
        private val flagRight : ImageView = view.findViewById(R.id.iv_flag_right)
        private val timeGame : TextView = view.findViewById(R.id.time)
        private val gameData : TextView = view.findViewById(R.id.tv_data)


        fun bind(resultMatch : ResultMatchesModel){
            timeGame.text = resultMatch.time
            nameLeft.text = resultMatch.nameLeft
            nameRight.text = resultMatch.nameRight
            gameData.text = resultMatch.date

            Glide.with(itemView.context)
                .load(resultMatch.flagLeft)
                .override(50, 50)
                .into(flagLeft)
            Glide.with(itemView.context)
                .load(resultMatch.flagRight)
                .override(50, 50)
                .into(flagRight)

            itemView.setOnClickListener {
                val dialog  = Dialog(itemView.context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.detailed_match)
                dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

                dialog.show()
            }

        }

    }
}