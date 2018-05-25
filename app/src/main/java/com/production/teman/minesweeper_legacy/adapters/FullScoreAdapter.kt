package com.production.teman.minesweeper_legacy.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.thirdLayer.FullScoreActivity
import com.production.teman.minesweeper_legacy.thirdLayer.scoreMode
import kotlinx.android.synthetic.main.activity_scores_list_item.view.*

class FullScoreAdapter(private val dataset: Array<String>,
                       private val datasetDescription: Array<String>, val context: Context) :
        RecyclerView.Adapter<FullScoreAdapter.ViewHolder>() {

    override fun getItemCount() = dataset.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.textViewItemFull
        val textDescription = view.textViewDescriptionFull
        val button = view.floatingButtonItem
        val imageViewIcon = view.imageViewIconFull
        val imageView = view.imageViewItemFull
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_scores_list_item,
                parent, false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.textView?.text = dataset.get(position)
        holder?.textDescription?.text = datasetDescription.get(position)
        holder?.button?.setOnClickListener({
            scoreMode = position
            var context: Context = it.context
            context.startActivity(Intent(context, FullScoreActivity::class.java))
        })
        when (position) {
            0 -> {
                holder?.imageViewIcon.setImageResource(R.drawable.ic_mode_classic)
                holder?.imageView.setImageResource(R.drawable.ic_launcher_background)
            }
            1 -> {
                holder?.imageViewIcon.setImageResource(R.drawable.ic_mode_sandbox)
                holder?.imageView.setImageResource(R.drawable.ic_launcher_background)
            }
            2 -> {
                holder?.imageViewIcon.setImageResource(R.drawable.ic_mode_adventure)
                holder?.imageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }
}