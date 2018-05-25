package com.production.teman.minesweeper_legacy.secondLayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.production.teman.minesweeper_legacy.thirdLayer.AboutActivity
import com.production.teman.minesweeper_legacy.R
import java.io.File

private lateinit var floatingButtonBack: FloatingActionButton
private lateinit var decorView: View
private var uiOptions: Int = 0

private var clearFlag: Boolean = false
private lateinit var clearToast: Toast

class SettingsActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        fullscreenEnabler()

        floatingButtonBack = findViewById(R.id.floatingButtonBack)
        floatingButtonBack.setOnClickListener(this)

        val buttonClear: Button = findViewById(R.id.buttonClearScores)
        val buttonAbout: Button = findViewById(R.id.buttonAbout)
        buttonClear.setOnClickListener(this)
        buttonAbout.setOnClickListener(this)

        clearFlag = false
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.floatingButtonBack -> {
                finish()
            }
            R.id.buttonClearScores -> {
                if (!clearFlag) {
                    clearToast= Toast.makeText(this, R.string.clearScoresText, Toast.LENGTH_SHORT)
                    clearToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 650)
                    clearToast.show()
                    clearFlag = true
                } else {
                    var filePath = applicationInfo.dataDir
                    var fileName = ""
                    for (i in 0..2) {
                        when (i) {
                            0 -> fileName = "classic.score"
                            1 -> fileName = "sandbox.score"
                            2 -> fileName = "adventure.score"
                        }
                        var fileFull: File = File(filePath, fileName)
                        fileFull.writeText("")
                    }

                    clearToast= Toast.makeText(this, R.string.clearScoresTextTrue, Toast.LENGTH_SHORT)
                    clearToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 650)
                    clearToast.show()
                    clearFlag = false
                }
            }
            R.id.buttonAbout -> {
                startActivity(Intent(this, AboutActivity::class.java))
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
