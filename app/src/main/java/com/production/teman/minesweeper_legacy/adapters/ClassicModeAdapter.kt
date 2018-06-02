package com.production.teman.minesweeper_legacy.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.production.teman.minesweeper_legacy.thirdLayer.GamePresetsActivity
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.thirdLayer.gamemodeSelected
import kotlinx.android.synthetic.main.activity_classic_mode_item.view.*
import kotlinx.android.synthetic.main.activity_gamemode_list_item.view.*

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
            Toast.makeText(context, "" + holder?.textView?.text + ";Square", Toast.LENGTH_SHORT).show()
        })
    }
}