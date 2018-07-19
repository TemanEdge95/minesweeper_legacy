package com.production.teman.minesweeper_legacy.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.thirdLayer.GamePresetsActivity
import kotlinx.android.synthetic.main.activity_classic_mode_item.view.*

private var positionSaved: Int = 0

private var gamePresetsActivity: GamePresetsActivity = GamePresetsActivity()

class classicModeAdapter(private val dataset: Array<String>, val context: Context) :
        RecyclerView.Adapter<classicModeAdapter.ViewHolder>() {

    override fun getItemCount() = dataset.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textViewClassic
        val cardView = view.cardViewClassic
        val constraintLayout = view.constraintLayoutClassic
        val imageCheck = view.imageViewClassicCheck
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        positionSaved = 0
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_classic_mode_item,
                parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.textView?.text = dataset.get(position)
        holder?.cardView?.setOnClickListener({
            positionSaved = position
            notifyDataSetChanged()
        })
        if (positionSaved == position) {
            holder?.constraintLayout?.setBackgroundColor(Color.parseColor("#FF4081"))
            holder?.imageCheck.setImageResource(R.drawable.ic_classic_check)
            gamePresetsActivity.setFieldWidthAdapter(holder?.textView?.text?.split("x")?.get(0)!!.toInt())
            gamePresetsActivity.setFieldHeightAdapter(holder?.textView?.text?.split("x")?.get(1)!!.toInt())
        } else {
            holder?.constraintLayout?.setBackgroundColor(Color.parseColor("#777777"))
            holder?.imageCheck.setImageResource(R.drawable.ic_classic_uncheck)
        }
    }
}