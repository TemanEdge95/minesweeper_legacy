package com.production.teman.minesweeper_legacy.thirdLayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.production.teman.minesweeper_legacy.R
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit

private lateinit var floatingButtonBack: FloatingActionButton
private lateinit var decorView: View
private var uiOptions: Int = 0

var gamemodeSelected: Int = 0
private var backFlag: Boolean = false

lateinit var textViewPresets: TextView
lateinit var textViewPresetsHead: TextView

private lateinit var backToast: Toast

private lateinit var gamemodePresets: Array<String>
private lateinit var gamemodeHead: Array<String>

private lateinit var threadTimerPresets: Thread

class GamePresetsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_presets)

        fullscreenEnabler()

        gamemodePresets = applicationContext.resources.getStringArray(R.array.gmFullDescr)
        gamemodeHead = applicationContext.resources.getStringArray(R.array.gamemodes)

        floatingButtonBack = findViewById(R.id.floatingButtonBack)
        floatingButtonBack.setOnClickListener(this)

        backFlag = false

        textViewPresets = findViewById(R.id.textViewPresets)
        textViewPresets.text = gamemodePresets.get(gamemodeSelected)

        textViewPresetsHead = findViewById(R.id.textViewPresetsHead)
        textViewPresetsHead.text = gamemodeHead.get(gamemodeSelected)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.floatingButtonBack -> backPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun backPressed() {
        timerForFlag()
        if (!backFlag) {
            backToast = Toast.makeText(this, R.string.backPresets, Toast.LENGTH_SHORT)
            backToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 650)
            backToast.show()
            backFlag = true
        } else {
            finish()
        }
    }

    fun timerForFlag() {
        threadTimerPresets = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(30))
                    backFlag = false
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
        threadTimerPresets.start()
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
