package com.lievito.crosta.scaffale.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.lievito.crosta.scaffale.data.PrefKeys
import com.lievito.crosta.scaffale.data.prefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != "android.intent.action.BOOT_COMPLETED") return
        runBlocking {
            val prefs = context.prefs.data.first()
            val enabled = prefs[PrefKeys.tipsEnabled] ?: true
            val pane = prefs[PrefKeys.tipsPaneDelGiorno] ?: true
            val scaffale = prefs[PrefKeys.tipsScaffalePronto] ?: true
            TipScheduler.sync(context, enabled, pane, scaffale)
        }
    }
}
