package com.lievito.crosta.scaffale.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.prefs by preferencesDataStore(name = "lievito28_prefs")

object PrefKeys {
    val favorites = stringPreferencesKey("favorites") // CSV ids
    fun statusKey(id: String) = stringPreferencesKey("status_$id")

    val tipsEnabled = booleanPreferencesKey("tips_enabled")
    val tipsPaneDelGiorno = booleanPreferencesKey("tips_pane_del_giorno")
    val tipsScaffalePronto = booleanPreferencesKey("tips_scaffale_pronto")
}
