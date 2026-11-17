package com.lievito.crosta.scaffale

import android.app.Application
import com.lievito.crosta.scaffale.notifications.TipNotifications

class ScaffaleLievitoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TipNotifications.ensureChannel(this)
    }
}
