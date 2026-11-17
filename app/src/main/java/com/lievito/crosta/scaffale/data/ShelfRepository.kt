package com.lievito.crosta.scaffale.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.lievito.crosta.scaffale.model.ShelfStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShelfRepository(private val context: Context) {
    val piatti = LocalContent.piatti
    val glossario = LocalContent.glossario
    val collezioni = LocalContent.collezioni

    fun getPlate(id: String) = piatti.firstOrNull { it.id == id }

    val libraryFlow: Flow<LibrarySnapshot> = context.prefs.data.map { prefs ->
        val favorites = prefs[PrefKeys.favorites]
            ?.split(',')
            ?.map { it.trim() }
            ?.filter { it.isNotBlank() }
            ?.toSet()
            ?: emptySet()

        val statuses = buildMap {
            piatti.forEach { plate ->
                val raw = prefs[PrefKeys.statusKey(plate.id)]
                val status = when (raw) {
                    ShelfStatus.IN_SCAFFALE.name -> ShelfStatus.IN_SCAFFALE
                    ShelfStatus.STUDIATA.name -> ShelfStatus.STUDIATA
                    else -> ShelfStatus.DA_STUDIARE
                }
                put(plate.id, status)
            }
        }
        LibrarySnapshot(favorites = favorites, statuses = statuses)
    }

    val favoritesFlow: Flow<Set<String>> = context.prefs.data.map { prefs ->
        prefs[PrefKeys.favorites]
            ?.split(',')
            ?.map { it.trim() }
            ?.filter { it.isNotBlank() }
            ?.toSet()
            ?: emptySet()
    }

    fun statusFlow(id: String): Flow<ShelfStatus> = context.prefs.data.map { prefs ->
        when (prefs[PrefKeys.statusKey(id)]) {
            ShelfStatus.IN_SCAFFALE.name -> ShelfStatus.IN_SCAFFALE
            ShelfStatus.STUDIATA.name -> ShelfStatus.STUDIATA
            else -> ShelfStatus.DA_STUDIARE
        }
    }

    val tipsEnabledFlow: Flow<Boolean> = context.prefs.data.map { it[PrefKeys.tipsEnabled] ?: true }
    val tipsPaneDelGiornoFlow: Flow<Boolean> = context.prefs.data.map { it[PrefKeys.tipsPaneDelGiorno] ?: true }
    val tipsScaffaleProntoFlow: Flow<Boolean> = context.prefs.data.map { it[PrefKeys.tipsScaffalePronto] ?: true }

    suspend fun setStatus(id: String, status: ShelfStatus) {
        context.prefs.edit { it[PrefKeys.statusKey(id)] = status.name }
    }

    suspend fun toggleFavorite(id: String) {
        context.prefs.edit { prefs ->
            val set = (prefs[PrefKeys.favorites] ?: "")
                .split(',')
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .toMutableSet()

            if (!set.add(id)) set.remove(id)
            prefs[PrefKeys.favorites] = set.sorted().joinToString(",")
        }
    }

    suspend fun setTipsEnabled(enabled: Boolean) {
        context.prefs.edit { it[PrefKeys.tipsEnabled] = enabled }
    }

    suspend fun setTipPaneDelGiorno(enabled: Boolean) {
        context.prefs.edit { it[PrefKeys.tipsPaneDelGiorno] = enabled }
    }

    suspend fun setTipScaffalePronto(enabled: Boolean) {
        context.prefs.edit { it[PrefKeys.tipsScaffalePronto] = enabled }
    }
}
