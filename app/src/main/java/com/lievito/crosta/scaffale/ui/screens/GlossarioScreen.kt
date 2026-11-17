package com.lievito.crosta.scaffale.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lievito.crosta.scaffale.R
import com.lievito.crosta.scaffale.data.ShelfRepository
import com.lievito.crosta.scaffale.ui.components.MuseumScaffold

@Composable
fun GlossarioScreen(
    repo: ShelfRepository,
    onBack: () -> Unit,
) {
    val list = repo.glossario.sortedBy { it.termine.lowercase() }

    MuseumScaffold(
        titolo = stringResource(R.string.glossario_title),
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
                    text = stringResource(R.string.glossario_intro, list.size),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            items(count = list.size, key = { list[it].termine }) { idx ->
                val e = list[idx]
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                ) {
                    androidx.compose.foundation.layout.Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = e.termine,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = e.definizione,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 6.dp),
                        )
                    }
                }
            }
        }
    }
}
