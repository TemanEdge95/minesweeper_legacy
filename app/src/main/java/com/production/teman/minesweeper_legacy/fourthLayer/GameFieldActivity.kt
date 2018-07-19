package com.production.teman.minesweeper_legacy.fourthLayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.FloatingActionButton
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.firstLayer.MainActivity
import com.production.teman.minesweeper_legacy.secondLayer.SettingsActivity

private var modeType: String = ""
private var fieldType: String = ""
private var fieldWidth: Int = 0
private var fieldHeight: Int = 0

private lateinit var floatingButtonPause: FloatingActionButton

private var backToMenuFlag: Boolean = false
private lateinit var returnToast: Toast

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
        var bottomSheetBuilder: BottomSheetDialog = BottomSheetDialog(this)

        var bottomSheetView: View = layoutInflater.inflate(R.layout.bottom_sheet_pause_menu, null)

        var buttonBackMenu: FloatingActionButton ?= bottomSheetView.findViewById(R.id.floatingActionButtonMenu)
        var buttonSettings: FloatingActionButton ?= bottomSheetView.findViewById(R.id.floatingActionButtonSettings)
        var buttonUnpause: FloatingActionButton ?= bottomSheetView.findViewById(R.id.floatingActionButtonUnpause)

        buttonBackMenu?.setOnClickListener { view ->
            if (!backToMenuFlag) {
                backToMenuFlag = true
                returnToast = Toast.makeText(this, R.string.toastReturn, Toast.LENGTH_SHORT)
                returnToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 550)
                returnToast.show()
            }
            else {
                backToMenuFlag = false
                bottomSheetBuilder.dismiss()
                startActivity(Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            }
        }

        buttonSettings?.setOnClickListener { view ->
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        buttonUnpause?.setOnClickListener { view ->
            backToMenuFlag = false
            bottomSheetBuilder.dismiss()
            fullscreenEnabler()
        }

        var window = bottomSheetBuilder.window
        window.clearFlags(
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS or
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        window.addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        window.decorView.systemUiVisibility =

                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN

        bottomSheetBuilder.setCanceledOnTouchOutside(false)
        bottomSheetBuilder.setContentView(bottomSheetView)
        bottomSheetBuilder.create()
        bottomSheetBuilder.show()
    }

    override fun onResume() {
        super.onResume()

        fullscreenEnabler()
    }
}
