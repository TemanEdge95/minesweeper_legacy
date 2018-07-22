package com.production.teman.minesweeper_legacy.firstLayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.secondLayer.PlayActivity
import com.production.teman.minesweeper_legacy.secondLayer.ScoresActivity
import com.production.teman.minesweeper_legacy.secondLayer.SettingsActivity
import java.io.File
import java.util.concurrent.TimeUnit
import com.production.teman.minesweeper_legacy.Support.BackgroundSoundService

lateinit var file: File

private var exitFlag: Boolean = false
private lateinit var threadTimerMain: Thread

private lateinit var serviceSound: Intent

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

        checkScoreFiles()

        //serviceSound = Intent(this, BackgroundSoundService::class.java)
        //startService(serviceSound)
    }

    private fun checkScoreFiles() {
        var filePath = applicationInfo.dataDir
        var fileName = ""
        for (i in 0..2) {
            when (i) {
                0 -> fileName = "classic.score"
                1 -> fileName = "sandbox.score"
                2 -> fileName = "adventure.score"
            }
            var fileFull: File = File(filePath, fileName)
            if (!fileFull.exists()) fileFull.createNewFile()
        }
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
        exitFlag = false
    }

    private fun fullscreenEnabler() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        timerForFlag()
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!exitFlag) {
                Toast.makeText(applicationContext, R.string.backToastText, Toast.LENGTH_SHORT).show()
                exitFlag = true
            } else System.exit(0)

            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun timerForFlag() {
        threadTimerMain = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(30))
                    exitFlag = false
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        threadTimerMain.start()
    }

    override fun onResume() {
        super.onResume()
        fullscreenEnabler()
    }
}
