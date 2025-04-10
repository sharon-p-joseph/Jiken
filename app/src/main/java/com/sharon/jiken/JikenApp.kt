package com.sharon.jiken

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JikenApp : Application() {

    companion object {
        lateinit var instance: JikenApp
    }
}