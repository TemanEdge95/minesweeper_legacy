package com.production.teman.minesweeper_legacy.models

import android.graphics.drawable.Drawable
import com.production.teman.minesweeper_legacy.R

class Player {
    var name: String = ""
    var title: String = ""
    var score: String = ""

    constructor() {}

    constructor(name: String, title: String, score: String) {
        this.name = name
        this.title = title
        this.score = score
    }
}