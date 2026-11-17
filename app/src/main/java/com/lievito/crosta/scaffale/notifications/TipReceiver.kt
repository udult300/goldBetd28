package com.lievito.crosta.scaffale.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TipReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        TipScheduler.triggerNow(context)
    }
}
