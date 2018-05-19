package com.production.teman.minesweeper_legacy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.production.teman.minesweeper_legacy.firstLayer.MainActivity

private lateinit var floatingButtonBack: FloatingActionButton
private lateinit var decorView: View
private var uiOptions: Int = 0
private lateinit var thread: Thread
private lateinit var imageViewSplash: ImageView

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        fullscreenEnabler()

        imageViewSplash = findViewById(R.id.imageViewSplashScreen)
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
        decorView = window.decorView
        uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions
    }

    override fun onResume() {
        super.onResume()

        fullscreenEnabler()
    }
}
