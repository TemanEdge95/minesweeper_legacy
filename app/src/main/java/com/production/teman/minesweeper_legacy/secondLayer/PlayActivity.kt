package com.production.teman.minesweeper_legacy.secondLayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.adapters.gamemodeAdapter

private lateinit var floatingButtonBack: FloatingActionButton
private lateinit var decorView: View
private var uiOptions: Int = 0

private lateinit var rv: RecyclerView
private lateinit var rvAdapter: RecyclerView.Adapter<*>
private lateinit var rvManager: RecyclerView.LayoutManager
private lateinit var snapHelper: LinearSnapHelper

private lateinit var gamemodeList: Array<String>
private lateinit var gamemodeDescription: Array<String>

class PlayActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        fullscreenEnabler()

        gamemodeList = applicationContext.resources.getStringArray(R.array.gamemodes)
        gamemodeDescription = applicationContext.resources.getStringArray(R.array.gmDescr)

        floatingButtonBack = findViewById(R.id.floatingButtonBack)
        floatingButtonBack.setOnClickListener(this)

        rvManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAdapter = gamemodeAdapter(gamemodeList, gamemodeDescription, this)

        rv = findViewById<RecyclerView>(R.id.gamemodeRecycler).apply {
            setHasFixedSize(true)
            layoutManager = rvManager
            adapter = rvAdapter
        }

        snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv)

        rv.scrollToPosition(0)
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