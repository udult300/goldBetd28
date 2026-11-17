package com.lievito.crosta.scaffale.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lievito.crosta.scaffale.BuildConfig
import com.lievito.crosta.scaffale.R
import com.lievito.crosta.scaffale.data.ShelfRepository
import com.lievito.crosta.scaffale.notifications.TipScheduler
import com.lievito.crosta.scaffale.ui.components.MuseumScaffold
import kotlinx.coroutines.launch

@Composable
fun ImpostazioniScreen(
    repo: ShelfRepository,
    onBack: () -> Unit,
) {
    val ctx = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()

    val tipsEnabled by repo.tipsEnabledFlow.collectAsStateWithLifecycle(initialValue = true)
    val paneDelGiorno by repo.tipsPaneDelGiornoFlow.collectAsStateWithLifecycle(initialValue = true)
    val scaffalePronto by repo.tipsScaffaleProntoFlow.collectAsStateWithLifecycle(initialValue = true)

    MuseumScaffold(
        titolo = stringResource(R.string.impostazioni_title),
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
                SectionTitle(text = stringResource(R.string.impostazioni_tips))
                SettingSwitchRow(
                    title = stringResource(R.string.tips_enabled),
                    subtitle = stringResource(R.string.tips_enabled_sub),
                    checked = tipsEnabled,
                    onCheckedChange = { v ->
                        scope.launch {
                            repo.setTipsEnabled(v)
                            TipScheduler.sync(ctx, v, paneDelGiorno, scaffalePronto)
                        }
                    },
                )
                SettingSwitchRow(
                    title = stringResource(R.string.tip_pane_del_giorno),
                    subtitle = stringResource(R.string.tip_pane_del_giorno_sub),
                    checked = paneDelGiorno,
                    enabled = tipsEnabled,
                    onCheckedChange = { v ->
                        scope.launch {
                            repo.setTipPaneDelGiorno(v)
                            TipScheduler.sync(ctx, tipsEnabled, v, scaffalePronto)
                        }
                    },
                )
                SettingSwitchRow(
                    title = stringResource(R.string.tip_scaffale_pronto),
                    subtitle = stringResource(R.string.tip_scaffale_pronto_sub),
                    checked = scaffalePronto,
                    enabled = tipsEnabled,
                    onCheckedChange = { v ->
                        scope.launch {
                            repo.setTipScaffalePronto(v)
                            TipScheduler.sync(ctx, tipsEnabled, paneDelGiorno, v)
                        }
                    },
                )
            }

            item {
                SectionTitle(text = stringResource(R.string.impostazioni_allergeni))
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                ) {
                    Text(
                        text = stringResource(R.string.allergen_disclaimer),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(14.dp),
                    )
                }
            }

            item {
                SectionTitle(text = stringResource(R.string.impostazioni_info))
                InfoRow(label = "Versione", value = BuildConfig.VERSION_NAME)
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(top = 6.dp, bottom = 2.dp),
    )
}

@Composable
private fun SettingSwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            androidx.compose.foundation.layout.Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Text(
                    subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }
            Spacer(Modifier.width(10.dp))
            Switch(
                checked = checked,
                enabled = enabled,
                onCheckedChange = onCheckedChange,
            )
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.padding(top = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
