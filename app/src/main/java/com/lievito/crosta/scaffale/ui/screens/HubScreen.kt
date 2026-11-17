package com.lievito.crosta.scaffale.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lievito.crosta.scaffale.R
import com.lievito.crosta.scaffale.data.LibrarySnapshot
import com.lievito.crosta.scaffale.data.ShelfRepository
import com.lievito.crosta.scaffale.model.ShelfStatus
import com.lievito.crosta.scaffale.ui.components.MuseumScaffold

@Composable
fun HubScreen(
    repo: ShelfRepository,
    onGoCatalogo: () -> Unit,
    onGoScaffale: () -> Unit,
    onGoSegnati: () -> Unit,
    onGoImpostazioni: () -> Unit,
) {
    val initial = LibrarySnapshot(
        favorites = emptySet(),
        statuses = repo.piatti.associate { it.id to ShelfStatus.DA_STUDIARE },
    )
    val snap by repo.libraryFlow.collectAsStateWithLifecycle(initialValue = initial)

    val total = repo.piatti.size
    val nScaffale = snap.statuses.values.count { it == ShelfStatus.IN_SCAFFALE }
    val nStudiata = snap.statuses.values.count { it == ShelfStatus.STUDIATA }
    val nSegnati = snap.favorites.size

    MuseumScaffold(
        titolo = stringResource(R.string.hub_title),
        canBack = false,
    ) { padding ->
        HubBody(
            padding = padding,
            total = total,
            nScaffale = nScaffale,
            nStudiata = nStudiata,
            nSegnati = nSegnati,
            onGoCatalogo = onGoCatalogo,
            onGoScaffale = onGoScaffale,
            onGoSegnati = onGoSegnati,
            onGoImpostazioni = onGoImpostazioni,
        )
    }
}

@Composable
private fun HubBody(
    padding: PaddingValues,
    total: Int,
    nScaffale: Int,
    nStudiata: Int,
    nSegnati: Int,
    onGoCatalogo: () -> Unit,
    onGoScaffale: () -> Unit,
    onGoSegnati: () -> Unit,
    onGoImpostazioni: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(padding),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            top = 10.dp,
            bottom = 18.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            Text(
                text = stringResource(R.string.hub_subtitle, total, nStudiata),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 2.dp),
            )
        }

        item {
            HubEntryCard(
                title = stringResource(R.string.hub_catalogo),
                subtitle = stringResource(R.string.hub_catalogo_sub, total),
                icon = Icons.Outlined.MenuBook,
                onClick = onGoCatalogo,
            )
        }
        item {
            HubEntryCard(
                title = stringResource(R.string.hub_scaffale),
                subtitle = stringResource(R.string.hub_scaffale_sub, nScaffale),
                icon = Icons.Outlined.Inventory2,
                onClick = onGoScaffale,
            )
        }
        item {
            HubEntryCard(
                title = stringResource(R.string.hub_segnati),
                subtitle = stringResource(R.string.hub_segnati_sub, nSegnati),
                icon = Icons.Outlined.StarOutline,
                onClick = onGoSegnati,
            )
        }
        item {
            HubEntryCard(
                title = stringResource(R.string.hub_impostazioni),
                subtitle = stringResource(R.string.hub_impostazioni_sub),
                icon = Icons.Outlined.Settings,
                onClick = onGoImpostazioni,
            )
        }
    }
}

@Composable
private fun HubEntryCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 10.dp),
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 6.dp),
            )
        }
    }
}
