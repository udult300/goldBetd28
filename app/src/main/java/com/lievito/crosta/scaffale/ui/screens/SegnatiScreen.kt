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
fun SegnatiScreen(
    repo: ShelfRepository,
    onBack: () -> Unit,
    onOpenPlate: (String) -> Unit,
) {
    val initial = remember(repo) {
        LibrarySnapshot(
            favorites = emptySet(),
            statuses = repo.piatti.associate { it.id to ShelfStatus.DA_STUDIARE },
        )
    }
    val snap by repo.libraryFlow.collectAsStateWithLifecycle(initialValue = initial)
    val scope = rememberCoroutineScope()

    val list = repo.piatti.filter { snap.favorites.contains(it.id) }

    MuseumScaffold(
        titolo = stringResource(R.string.segnati_title),
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
            if (list.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.segnati_empty),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                }
            } else {
                item {
                    Text(
                        text = stringResource(R.string.segnati_intro, list.size),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                items(count = list.size, key = { list[it].id }) { idx ->
                    val plate = list[idx]
                    val status = snap.statuses[plate.id] ?: ShelfStatus.DA_STUDIARE
                    PiattoCard(
                        plate = plate,
                        status = status,
                        favorite = true,
                        onClick = { onOpenPlate(plate.id) },
                        onToggleFavorite = { scope.launch { repo.toggleFavorite(plate.id) } },
                    )
                }
            }
        }
    }
}
