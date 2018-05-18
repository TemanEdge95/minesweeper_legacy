package com.production.teman.minesweeper_legacy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

private lateinit var floatingButtonBack: FloatingActionButton
private lateinit var decorView: View
private var uiOptions: Int = 0

var gamemodeSelected: Int = 0
var backFlag: Boolean = false
lateinit var textViewPresets: TextView

lateinit var backToast: Toast

var gamemodePresets: Array<String> = arrayOf("Classic mode includes 3 presets. " +
        "Just pick 1 of them and play. Have fun!", "Sandbox mode includes lots of presets for you." +
        "Be careful with them, they can make your game hard enough.",
        "Adventure mode is currently in develop. Stay tuned!")

class GamePresetsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_presets)

        fullscreenEnabler()

        floatingButtonBack = findViewById(R.id.floatingButtonBack)
        floatingButtonBack.setOnClickListener(this)

        backFlag = false

        textViewPresets = findViewById(R.id.textViewPresets)
        textViewPresets.text = gamemodePresets.get(gamemodeSelected)
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
        if (!backFlag) {
            backToast = Toast.makeText(this, R.string.backPresets, Toast.LENGTH_SHORT)
            backToast.setGravity(Gravity.BOTTOM, 0, 150)
            backToast.show()
            backFlag = true
        } else {
            finish()
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
