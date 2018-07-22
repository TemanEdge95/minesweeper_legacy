package com.production.teman.minesweeper_legacy.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.models.Player

class FullScoreListViewAdapter(private var activity: Activity, private var items: ArrayList<Player>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var txtName: TextView? = null
        var txtTitle: TextView? = null
        var txtScore: TextView? = null
        var txtCounter: TextView? = null
        var imageView: ImageView? = null

        init {
            this.txtName = row?.findViewById(R.id.textViewName)
            this.txtTitle = row?.findViewById(R.id.textViewTitle)
            this.txtScore = row?.findViewById(R.id.textViewScore)
            this.txtCounter = row?.findViewById(R.id.textViewCounter)
            this.imageView = row?.findViewById(R.id.imageViewPlace)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.activity_full_score_list_item, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var player = items[position]
        viewHolder.txtName?.text = player.name
        viewHolder.txtTitle?.text = player.title
        viewHolder.txtScore?.text = player.score
        viewHolder.txtCounter?.text = (position + 1).toString()

        when (position) {
            0 -> viewHolder.imageView?.setImageResource(R.drawable.ic_trophy_first)
            1 -> viewHolder.imageView?.setImageResource(R.drawable.ic_trophy_second)
            2 -> viewHolder.imageView?.setImageResource(R.drawable.ic_trophy_third)
        }

        viewHolder.imageView?.visibility = if (position > 2) View.INVISIBLE else View.VISIBLE

        return view as View
    }

    override fun getItem(i: Int): Player {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}