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
fun ScaffaleScreen(
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

    val inScaffale = repo.piatti.filter { snap.statuses[it.id] == ShelfStatus.IN_SCAFFALE }
    val studiate = repo.piatti.filter { snap.statuses[it.id] == ShelfStatus.STUDIATA }

    MuseumScaffold(
        titolo = stringResource(R.string.scaffale_title),
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
            item {
                Text(
                    text = stringResource(R.string.scaffale_intro),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            if (inScaffale.isNotEmpty()) {
                item { SectionHeader(text = stringResource(R.string.scaffale_in_scaffale, inScaffale.size)) }
                items(count = inScaffale.size, key = { inScaffale[it].id }) { idx ->
                    val plate = inScaffale[idx]
                    PiattoCard(
                        plate = plate,
                        status = ShelfStatus.IN_SCAFFALE,
                        favorite = snap.favorites.contains(plate.id),
                        onClick = { onOpenPlate(plate.id) },
                        onToggleFavorite = { scope.launch { repo.toggleFavorite(plate.id) } },
                    )
                }
            } else {
                item { EmptyHint(text = stringResource(R.string.scaffale_empty)) }
            }

            if (studiate.isNotEmpty()) {
                item { SectionHeader(text = stringResource(R.string.scaffale_studiate, studiate.size)) }
                items(count = studiate.size, key = { studiate[it].id }) { idx ->
                    val plate = studiate[idx]
                    PiattoCard(
                        plate = plate,
                        status = ShelfStatus.STUDIATA,
                        favorite = snap.favorites.contains(plate.id),
                        onClick = { onOpenPlate(plate.id) },
                        onToggleFavorite = { scope.launch { repo.toggleFavorite(plate.id) } },
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp),
    )
}

@Composable
private fun EmptyHint(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
    )
}
