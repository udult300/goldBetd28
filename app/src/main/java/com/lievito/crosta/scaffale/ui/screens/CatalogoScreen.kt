package com.lievito.crosta.scaffale.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.lievito.crosta.scaffale.ui.components.EmptyState
import com.lievito.crosta.scaffale.ui.components.SearchField
import kotlinx.coroutines.launch

@Composable
fun CatalogoScreen(
    repo: ShelfRepository,
    onBack: () -> Unit,
    onOpenPlate: (String) -> Unit,
    onOpenCollezioni: () -> Unit,
    onOpenGlossario: () -> Unit,
) {
    val initial = remember(repo) {
        LibrarySnapshot(
            favorites = emptySet(),
            statuses = repo.piatti.associate { it.id to ShelfStatus.DA_STUDIARE },
        )
    }
    val snap by repo.libraryFlow.collectAsStateWithLifecycle(initialValue = initial)
    val scope = rememberCoroutineScope()

    var q by rememberSaveable { mutableStateOf("") }
    val filtered = remember(q, repo.piatti) {
        val query = q.trim()
        if (query.isBlank()) repo.piatti
        else repo.piatti.filter {
            it.titolo.contains(query, ignoreCase = true) ||
                it.sottotitolo.contains(query, ignoreCase = true) ||
                it.area.contains(query, ignoreCase = true) ||
                it.tecnica.contains(query, ignoreCase = true)
        }
    }

    MuseumScaffold(
        titolo = stringResource(R.string.catalogo_title),
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
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text(
                    text = stringResource(R.string.catalogo_intro),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            item {
                EntryLink(
                    title = stringResource(R.string.catalogo_collezioni),
                    subtitle = stringResource(R.string.catalogo_collezioni_sub, repo.collezioni.size),
                    icon = Icons.Outlined.CollectionsBookmark,
                    onClick = onOpenCollezioni,
                )
                Spacer(Modifier.height(10.dp))
                EntryLink(
                    title = stringResource(R.string.catalogo_glossario),
                    subtitle = stringResource(R.string.catalogo_glossario_sub, repo.glossario.size),
                    icon = Icons.Outlined.Abc,
                    onClick = onOpenGlossario,
                )
            }

            item {
                SearchField(
                    value = q,
                    onValueChange = { q = it },
                    placeholder = stringResource(R.string.search_label),
                    leadingIcon = Icons.Outlined.Search,
                )
            }

            item {
                Text(
                    text = stringResource(R.string.catalogo_count, filtered.size),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }

            if (filtered.isEmpty()) {
                item {
                    EmptyState(
                        title = stringResource(R.string.catalogo_count, 0),
                        message = stringResource(R.string.not_found),
                        icon = Icons.Outlined.SearchOff,
                    )
                }
            } else {
                items(count = filtered.size, key = { filtered[it].id }) { idx ->
                    val plate = filtered[idx]
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

@Composable
private fun EntryLink(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
) {
    androidx.compose.material3.Card(
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
        ) {
            androidx.compose.material3.Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            androidx.compose.foundation.layout.Spacer(Modifier.width(12.dp))
            androidx.compose.foundation.layout.Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            androidx.compose.material3.Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
