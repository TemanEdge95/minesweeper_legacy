package com.production.teman.minesweeper_legacy.Support

import android.content.Intent
import android.os.IBinder
import android.app.Service
import android.media.MediaPlayer
import com.production.teman.minesweeper_legacy.R

class BackgroundSoundService : Service() {
    lateinit var player: MediaPlayer
    override fun onBind(arg0: Intent): IBinder? {

        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.need_some1)
        player.isLooping = true
        player.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player.start()
        return Service.START_STICKY
    }

    override fun onStart(intent: Intent, startId: Int) {
    }

    override fun onDestroy() {
        player.stop()
        player.release()
    }

    override fun onLowMemory() {
    }

    companion object {
        private val TAG: String? = null
    }
}