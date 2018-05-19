package com.production.teman.minesweeper_legacy.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.production.teman.minesweeper_legacy.thirdLayer.GamePresetsActivity
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.thirdLayer.gamemodeSelected
import kotlinx.android.synthetic.main.activity_gamemode_list_item.view.*

class gamemodeAdapter(private val dataset: Array<String>,
                      private val datasetDescription: Array<String>, val context: Context) :
        RecyclerView.Adapter<gamemodeAdapter.ViewHolder>() {

    override fun getItemCount() = dataset.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textViewItem
        val textDescription = view.textViewDescription
        val button = view.floatingButtonItem
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_gamemode_list_item,
                parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.textView?.text = dataset.get(position)
        holder?.textDescription?.text = datasetDescription.get(position)
        holder?.button?.setOnClickListener({
            gamemodeSelected = position
            var context: Context = it.context
            context.startActivity(Intent(context, GamePresetsActivity::class.java))
        })
    }
}