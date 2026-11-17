package com.lievito.crosta.scaffale.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lievito.crosta.scaffale.R
import com.lievito.crosta.scaffale.data.LibrarySnapshot
import com.lievito.crosta.scaffale.data.ShelfRepository
import com.lievito.crosta.scaffale.model.ShelfStatus
import com.lievito.crosta.scaffale.ui.components.MuseumScaffold
import com.lievito.crosta.scaffale.ui.components.HeroHeader
import com.lievito.crosta.scaffale.ui.components.SectionHeader
import com.lievito.crosta.scaffale.ui.components.TonalSection
import kotlinx.coroutines.launch

@Composable
fun PiattoDettaglioScreen(
    repo: ShelfRepository,
    id: String,
    onBack: () -> Unit,
) {
    val plate = repo.getPlate(id)
    if (plate == null) {
        MuseumScaffold(
            titolo = stringResource(R.string.dettaglio_title),
            canBack = true,
            onBack = onBack,
        ) { padding ->
            Text(
                text = stringResource(R.string.not_found),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp, vertical = 24.dp),
            )
        }
        return
    }

    val initial = remember(repo) {
        LibrarySnapshot(
            favorites = emptySet(),
            statuses = repo.piatti.associate { it.id to ShelfStatus.DA_STUDIARE },
        )
    }
    val snap by repo.libraryFlow.collectAsStateWithLifecycle(initialValue = initial)
    val scope = rememberCoroutineScope()

    val status = snap.statuses[plate.id] ?: ShelfStatus.DA_STUDIARE
    val favorite = snap.favorites.contains(plate.id)

    MuseumScaffold(
        titolo = stringResource(R.string.dettaglio_title),
        canBack = true,
        onBack = onBack,
        actions = {
            val scale = animateFloatAsState(if (favorite) 1.06f else 1f, label = "favScaleTop")
            androidx.compose.material3.IconButton(onClick = { scope.launch { repo.toggleFavorite(plate.id) } }) {
                androidx.compose.material3.Icon(
                    imageVector = if (favorite) {
                        Icons.Filled.Star
                    } else {
                        Icons.Outlined.StarOutline
                    },
                    contentDescription = stringResource(R.string.favorite_toggle),
                    tint = if (favorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.scale(scale.value),
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            HeroHeader(
                title = plate.titolo,
                chips = listOf(status.labelIt, plate.area, plate.tecnica, "${plate.minuti} min"),
            )

            TonalSection {
                SectionHeader(title = plate.sottotitolo)
                if (plate.allergeni.isNotBlank()) {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = plate.allergeni,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Spacer(Modifier.height(12.dp))
                StatusRow(
                    current = status,
                    onSet = { s -> scope.launch { repo.setStatus(plate.id, s) } },
                )
            }

            TonalSection {
                SectionHeader(title = stringResource(R.string.key_points))
                Spacer(Modifier.height(8.dp))
                plate.punti.forEach { point ->
                    Text(
                        text = "• $point",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            TonalSection {
                SectionHeader(title = stringResource(R.string.testo))
                Spacer(Modifier.height(8.dp))
                plate.testo.forEach { p ->
                    Text(
                        text = p,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 10.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusRow(
    current: ShelfStatus,
    onSet: (ShelfStatus) -> Unit,
) {
    val options = listOf(
        ShelfStatus.DA_STUDIARE,
        ShelfStatus.IN_SCAFFALE,
        ShelfStatus.STUDIATA,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        options.forEach { s ->
            val selected = current == s
            OutlinedButton(
                onClick = { onSet(s) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                ),
            ) {
                Text(
                    text = s.labelIt,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
                )
            }
        }
    }
}
