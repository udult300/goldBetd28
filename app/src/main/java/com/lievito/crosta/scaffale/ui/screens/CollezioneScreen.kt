package com.lievito.crosta.scaffale.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lievito.crosta.scaffale.R
import com.lievito.crosta.scaffale.data.LibrarySnapshot
import com.lievito.crosta.scaffale.data.ShelfRepository
import com.lievito.crosta.scaffale.model.ShelfStatus
import com.lievito.crosta.scaffale.ui.components.MuseumScaffold
import com.lievito.crosta.scaffale.ui.components.PiattoCard
import kotlinx.coroutines.launch

@Composable
fun CollezioneScreen(
    repo: ShelfRepository,
    id: String,
    onBack: () -> Unit,
    onOpenPlate: (String) -> Unit,
) {
    val c = repo.collezioni.firstOrNull { it.id == id }
    val title = c?.titolo ?: stringResource(R.string.collezione_title)

    val initial = remember(repo) {
        LibrarySnapshot(
            favorites = emptySet(),
            statuses = repo.piatti.associate { it.id to ShelfStatus.DA_STUDIARE },
        )
    }
    val snap by repo.libraryFlow.collectAsStateWithLifecycle(initialValue = initial)
    val scope = rememberCoroutineScope()

    val plates = if (c == null) emptyList() else repo.piatti.filter { c.plateIds.contains(it.id) }

    MuseumScaffold(
        titolo = title,
        canBack = true,
        onBack = onBack,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 18.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            if (c == null) {
                item {
                    Text(
                        text = stringResource(R.string.not_found),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                }
            } else {
                item {
                    Text(
                        text = c.descrizione,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                item {
                    Text(
                        text = stringResource(R.string.catalogo_count, plates.size),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
                items(count = plates.size, key = { plates[it].id }) { idx ->
                    val plate = plates[idx]
                    val status = snap.statuses[plate.id] ?: ShelfStatus.DA_STUDIARE
                    val favorite = snap.favorites.contains(plate.id)
                    PiattoCard(
                        plate = plate,
                        status = status,
                        favorite = favorite,
                        onClick = { onOpenPlate(plate.id) },
                        onToggleFavorite = { scope.launch { repo.toggleFavorite(plate.id) } },
                    )
                }
            }
        }
    }
}
