package com.lievito.crosta.scaffale.notifications

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object TipScheduler {
    private const val WORK_DAILY = "lievito28_tips_daily"
    private const val WORK_NOW = "lievito28_tips_now"

    fun sync(
        context: Context,
        enabled: Boolean,
        paneDelGiorno: Boolean,
        scaffalePronto: Boolean,
    ) {
        if (!enabled || (!paneDelGiorno && !scaffalePronto)) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_DAILY)
            return
        }
        val req = PeriodicWorkRequestBuilder<TipWorker>(24, TimeUnit.HOURS)
            .addTag(WORK_DAILY)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_DAILY,
            ExistingPeriodicWorkPolicy.UPDATE,
            req,
        )
    }

    fun triggerNow(context: Context) {
        val req = OneTimeWorkRequestBuilder<TipWorker>()
            .addTag(WORK_NOW)
            .build()
        WorkManager.getInstance(context).enqueueUniqueWork(
            WORK_NOW,
            ExistingWorkPolicy.REPLACE,
            req,
        )
    }
}
