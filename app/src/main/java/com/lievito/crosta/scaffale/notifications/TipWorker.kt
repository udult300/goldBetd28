package com.lievito.crosta.scaffale.notifications

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lievito.crosta.scaffale.R
import com.lievito.crosta.scaffale.data.LocalContent
import com.lievito.crosta.scaffale.data.PrefKeys
import com.lievito.crosta.scaffale.data.prefs
import kotlinx.coroutines.flow.first
import kotlin.random.Random

class TipWorker(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        TipNotifications.ensureChannel(applicationContext)

        val prefs = applicationContext.prefs.data.first()
        val enabled = prefs[PrefKeys.tipsEnabled] ?: true
        val paneDelGiorno = prefs[PrefKeys.tipsPaneDelGiorno] ?: true
        val scaffalePronto = prefs[PrefKeys.tipsScaffalePronto] ?: true

        if (!enabled || (!paneDelGiorno && !scaffalePronto)) return Result.success()

        if (!TipNotifications.areNotificationsEnabled(applicationContext)) return Result.success()

        val plate = LocalContent.piatti.random(Random(System.currentTimeMillis()))
        val microTip = LocalContent.tips.random(Random(System.currentTimeMillis()))
        val candidates = buildList {
            if (paneDelGiorno) {
                add(
                    "Pane del giorno" to "Oggi studia: ${plate.titolo}. ${microTip}",
                )
            }
            if (scaffalePronto) {
                add(
                    "Scaffale pronto" to "Metti in scaffale: ${plate.titolo}. ${microTip}",
                )
            }
        }
        val (title, text) = candidates.random(Random(System.currentTimeMillis()))

        val n = NotificationCompat.Builder(applicationContext, TipNotifications.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_lievito)
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(text))
            .setAutoCancel(true)
            .build()

        androidx.core.app.NotificationManagerCompat
            .from(applicationContext)
            .notify(28_028, n)

        return Result.success()
    }
}
