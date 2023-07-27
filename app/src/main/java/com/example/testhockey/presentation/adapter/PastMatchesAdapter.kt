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

            //openDetailsPastMatches()

            itemView.setOnClickListener {
                val dialog  = Dialog(itemView.context, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setContentView(R.layout.detailed_match)
                dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

                val time : TextView = dialog.findViewById(R.id.tv_time_details)
                val flagLeft : ImageView = dialog.findViewById(R.id.iv_flag_left_details)
                val flagRight : ImageView = dialog.findViewById(R.id.iv_flag_right_details)
                val result : TextView = dialog.findViewById(R.id.tv_result_details)
                val nameLeft : TextView = dialog.findViewById(R.id.tv_name_left_details)
                val nameRight : TextView = dialog.findViewById(R.id.tv_name_right_details)
                val countryLeft : TextView = dialog.findViewById(R.id.tv_country_left_details)
                val countryRight : TextView = dialog.findViewById(R.id.tv_country_right_details)
                val bt_back : ImageView = dialog.findViewById(R.id.iv_back_home)

                time.text = resultMatch.time
                result.text = resultMatch.score
                nameLeft.text = resultMatch.nameLeft
                nameRight.text = resultMatch.nameRight
                countryLeft.text = resultMatch.countryLeft
                countryRight.text = resultMatch.countryRight

                Glide.with(itemView.context)
                    .load(resultMatch.flagLeft)
                    .override(70, 70)
                    .into(flagLeft)
                Glide.with(itemView.context)
                    .load(resultMatch.flagRight)
                    .override(70, 70)
                    .into(flagRight)

                dialog.show()

                bt_back.setOnClickListener { dialog.cancel() }


            }
        }
    }
}