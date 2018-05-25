package com.production.teman.minesweeper_legacy.thirdLayer

import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.adapters.FullScoreListViewAdapter
import com.production.teman.minesweeper_legacy.models.Player
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*
import java.util.Collections.swap
import kotlin.collections.ArrayList


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
lateinit var permToast: Toast

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

        lvAdapter = FullScoreListViewAdapter(this, getData())
        listView?.adapter = lvAdapter
        lvAdapter?.notifyDataSetChanged()
    }

    fun getData(): ArrayList<Player> {
        var result = ArrayList<Player>()

        var filePath = applicationInfo.dataDir
        var fileName = ""
            when (scoreMode) {
                0 -> fileName = "classic.score"
                1 -> fileName = "sandbox.score"
                2 -> fileName = "adventure.score"
            }
        var fileFull: File = File(filePath, fileName)

        var dataString = fileFull.readText()
        var dataSplit = dataString.split(";")
        dataSplit = dataSplit.dropLast(1)

        if (dataSplit.size != 1) {
            for (i in 0..(dataSplit.size - 1) step 3) {
                var player: Player = Player(dataSplit[i], dataSplit[i + 1], dataSplit[i + 2])
                result.add(player)
            }
        }

        result.sortByDescending { it.score.toInt() }

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
