package com.production.teman.minesweeper_legacy

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast

var exitFlag: Boolean = false

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fullscreenEnabler()

        val buttonPlay: Button = findViewById(R.id.buttonPlay)
        val buttonScores: Button = findViewById(R.id.buttonScores)
        val buttonSettings: Button = findViewById(R.id.buttonSettings)

        buttonPlay.setOnClickListener(this)
        buttonScores.setOnClickListener(this)
        buttonSettings.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonPlay -> {
                startActivity(Intent(this, PlayActivity::class.java))
            }
            R.id.buttonScores -> {
                startActivity(Intent(this, ScoresActivity::class.java))
            }
            R.id.buttonSettings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
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
            if (!exitFlag) {
                Toast.makeText(applicationContext, R.string.backToastText, Toast.LENGTH_SHORT).show()
                exitFlag = true
            } else System.exit(0)

            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
