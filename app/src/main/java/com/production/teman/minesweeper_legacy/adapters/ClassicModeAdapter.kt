package com.production.teman.minesweeper_legacy.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.fourthLayer.GameFieldActivity
import kotlinx.android.synthetic.main.activity_classic_mode_item.view.*

var gameFieldActivity: GameFieldActivity = GameFieldActivity()

class classicModeAdapter(private val dataset: Array<String>, val context: Context) :
        RecyclerView.Adapter<classicModeAdapter.ViewHolder>() {



    override fun getItemCount() = dataset.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textViewClassic
        val button = view.buttonPickClassic
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_classic_mode_item,
                parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.textView?.text = dataset.get(position)
        holder?.button?.setOnClickListener({
            gameFieldActivity.setFieldParams("Classic","Square",
                    holder?.textView?.text?.split("x")?.get(0)!!.toInt(),
                    holder?.textView?.text?.split("x")?.get(1)!!.toInt())
            var context: Context = it.context
            context.startActivity(Intent(context, GameFieldActivity::class.java))
        })
    }
}