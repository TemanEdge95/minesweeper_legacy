package com.production.teman.minesweeper_legacy.thirdLayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.adapters.FullScoreListViewAdapter
import com.production.teman.minesweeper_legacy.models.Player


private lateinit var floatingButtonBack: FloatingActionButton
private lateinit var decorView: View
private var uiOptions: Int = 0

private lateinit var gamemodeHead: Array<String>
private lateinit var textView: TextView

private lateinit var listView: ListView
private lateinit var lvAdapter: FullScoreListViewAdapter

private lateinit var emptyLayout: ConstraintLayout

private lateinit var imageViewIcon: ImageView

var scoreMode: Int = 0


class FullScoreActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_score)

        fullscreenEnabler()

        gamemodeHead = applicationContext.resources.getStringArray(R.array.gamemodes)

        floatingButtonBack = findViewById(R.id.floatingButtonBack)
        floatingButtonBack.setOnClickListener(this)

        textView = findViewById(R.id.textViewFullscoreHead)
        textView.text = gamemodeHead.get(scoreMode)

        imageViewIcon = findViewById(R.id.imageViewIcon)
        when (scoreMode) {
            0 -> imageViewIcon.setImageResource(R.drawable.ic_launcher_background)
            1 -> imageViewIcon.setImageResource(R.drawable.ic_launcher_background)
            2 -> imageViewIcon.setImageResource(R.drawable.ic_launcher_background)
        }

        listView = findViewById(R.id.listViewFullScore)

        emptyLayout = findViewById(R.id.layoutEmpty)

        lvAdapter = FullScoreListViewAdapter(this, generateData())
        listView?.adapter = lvAdapter
        lvAdapter?.notifyDataSetChanged()
    }

    fun generateData(): ArrayList<Player> {
        var result = ArrayList<Player>()

        when (scoreMode) {
            0 -> {
                for (i in 0..4) {
                    var player: Player = Player("Name", "Title", "1000")
                    result.add(player)
                }
            }
            1 -> {
                var player: Player = Player("Name", "Title", "1000")
                result.add(player)
            }
        }

        if (result.isEmpty()) emptyLayout.visibility = View.VISIBLE
        else emptyLayout.visibility = View.INVISIBLE

        return result
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.floatingButtonBack -> {
                finish()
            }
        }
    }

    private fun fullscreenEnabler() {
        decorView = window.decorView
        uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun onResume() {
        super.onResume()

        fullscreenEnabler()
    }
}
