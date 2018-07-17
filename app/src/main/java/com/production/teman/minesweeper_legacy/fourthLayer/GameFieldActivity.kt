package com.production.teman.minesweeper_legacy.fourthLayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.Support.BottomSheetPauseMenu

private var modeType: String = ""
private var fieldType: String = ""
private var fieldWidth: Int = 0
private var fieldHeight: Int = 0

private var bottomSheetPauseMenu: BottomSheetPauseMenu = BottomSheetPauseMenu()
private lateinit var floatingButtonPause: FloatingActionButton

class GameFieldActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_field)

        fullscreenEnabler()

        floatingButtonPause = findViewById(R.id.floatingButtonPauseGame)
        floatingButtonPause.setOnClickListener(this)

        //for Test
        var textViewTest: TextView = findViewById(R.id.textViewTest)
        textViewTest.text = modeType + "\n" + fieldType + "\n" + fieldWidth + " x " + fieldHeight
    }

    fun setFieldParams(mode: String, type: String, width: Int, height: Int) {
        modeType = mode
        fieldType = type
        fieldWidth = width
        fieldHeight = height
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.floatingButtonPauseGame -> {
              initPauseMenu()
            }
        }
    }

    private fun fullscreenEnabler() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            initPauseMenu()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun initPauseMenu() {
        bottomSheetPauseMenu.show(supportFragmentManager, "bottomSheetPauseMenu")
    }

    override fun onResume() {
        super.onResume()

        fullscreenEnabler()
    }
}
