package com.production.teman.minesweeper_legacy.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.production.teman.minesweeper_legacy.layers.thirdLayer.GamePresetsActivity
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.layers.thirdLayer.gamemodeSelected
import kotlinx.android.synthetic.main.activity_gamemode_list_item.view.*

class GamemodeAdapter(private val dataset: Array<String>,
                      private val datasetDescription: Array<String>, val context: Context) :
        RecyclerView.Adapter<GamemodeAdapter.ViewHolder>() {

    override fun getItemCount() = dataset.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textViewItem!!
        val textDescription = view.textViewDescription!!
        val button = view.floatingButtonItem!!
        val imageViewIcon = view.imageViewIcon4!!
        val imageView = view.imageViewItem!!
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_gamemode_list_item,
                parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataset[position]
        holder.textDescription.text = datasetDescription[position]
        holder.button.setOnClickListener({
            gamemodeSelected = position
            val context: Context = it.context
            context.startActivity(Intent(context, GamePresetsActivity::class.java))
        })

        holder.imageViewIcon.setImageResource(when (position) {
            0 -> R.drawable.ic_mode_classic
            1 -> R.drawable.ic_mode_sandbox
            else -> R.drawable.ic_mode_adventure
        })
        holder.imageView.setImageResource(when (position) {
            0 -> R.drawable.ic_launcher_background
            1 -> R.drawable.ic_launcher_background
            else -> R.drawable.ic_launcher_background
        })
    }
}