package com.production.teman.minesweeper_legacy.layers.zeroLayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.production.teman.minesweeper_legacy.R
import com.production.teman.minesweeper_legacy.layers.firstLayer.MainActivity

private lateinit var thread: Thread

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        fullscreenEnabler()

        val imageViewSplash: ImageView = findViewById(R.id.imageViewSplashScreen)
        imageViewSplash.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_anim))

        thread = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }

    private fun fullscreenEnabler() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun onResume() {
        super.onResume()

        fullscreenEnabler()
    }
}
