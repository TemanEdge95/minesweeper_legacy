package com.production.teman.minesweeper_legacy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_gamemode_list_item.view.*

class gamemodeAdapter(private val dataset: Array<String>,
                      private val datasetDescription: Array<String>, val context: Context) :
        RecyclerView.Adapter<gamemodeAdapter.ViewHolder>() {

    override fun getItemCount() = dataset.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textViewItem
        val textDescription = view.textViewDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): gamemodeAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_gamemode_list_item,
                parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.textView?.text = dataset.get(position)
        holder?.textDescription?.text = datasetDescription.get(position)
    }

}