package com.example.smartmeetingai

import android.app.Application
import com.example.smartmeetingai.sync.SyncScheduler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmartMeetingAIApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        SyncScheduler.schedule(this)
    }
}
