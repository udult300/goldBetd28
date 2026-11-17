package com.lievito.crosta.scaffale.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lievito.crosta.scaffale.data.ShelfRepository
import com.lievito.crosta.scaffale.ui.screens.CatalogoScreen
import com.lievito.crosta.scaffale.ui.screens.CollezioneScreen
import com.lievito.crosta.scaffale.ui.screens.CollezioniScreen
import com.lievito.crosta.scaffale.ui.screens.GlossarioScreen
import com.lievito.crosta.scaffale.ui.screens.HubScreen
import com.lievito.crosta.scaffale.ui.screens.ImpostazioniScreen
import com.lievito.crosta.scaffale.ui.screens.PiattoDettaglioScreen
import com.lievito.crosta.scaffale.ui.screens.ScaffaleScreen
import com.lievito.crosta.scaffale.ui.screens.SegnatiScreen

object Routes {
    const val HUB = "hub"
    const val CATALOGO = "catalogo"
    const val SCAFFALE = "scaffale"
    const val SEGNATI = "segnati"
    const val IMPOSTAZIONI = "impostazioni"
    const val DETTAGLIO = "dettaglio"
    const val COLLEZIONI = "collezioni"
    const val COLLEZIONE = "collezione"
    const val GLOSSARIO = "glossario"
}

@Composable
fun AppRoot() {
    val context = LocalContext.current.applicationContext
    val repo = remember { ShelfRepository(context) }
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Routes.HUB) {
        composable(Routes.HUB) {
            HubScreen(
                repo = repo,
                onGoCatalogo = { nav.navigate(Routes.CATALOGO) },
                onGoScaffale = { nav.navigate(Routes.SCAFFALE) },
                onGoSegnati = { nav.navigate(Routes.SEGNATI) },
                onGoImpostazioni = { nav.navigate(Routes.IMPOSTAZIONI) },
            )
        }
        composable(Routes.CATALOGO) {
            CatalogoScreen(
                repo = repo,
                onBack = { nav.popBackStack() },
                onOpenPlate = { nav.navigate("${Routes.DETTAGLIO}/$it") },
                onOpenCollezioni = { nav.navigate(Routes.COLLEZIONI) },
                onOpenGlossario = { nav.navigate(Routes.GLOSSARIO) },
            )
        }
        composable(Routes.SCAFFALE) {
            ScaffaleScreen(
                repo = repo,
                onBack = { nav.popBackStack() },
                onOpenPlate = { nav.navigate("${Routes.DETTAGLIO}/$it") },
            )
        }
        composable(Routes.SEGNATI) {
            SegnatiScreen(
                repo = repo,
                onBack = { nav.popBackStack() },
                onOpenPlate = { nav.navigate("${Routes.DETTAGLIO}/$it") },
            )
        }
        composable(Routes.IMPOSTAZIONI) {
            ImpostazioniScreen(
                repo = repo,
                onBack = { nav.popBackStack() },
            )
        }
        composable(
            route = "${Routes.DETTAGLIO}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            val id = it.arguments?.getString("id").orEmpty()
            PiattoDettaglioScreen(
                repo = repo,
                id = id,
                onBack = { nav.popBackStack() },
            )
        }
        composable(Routes.COLLEZIONI) {
            CollezioniScreen(
                repo = repo,
                onBack = { nav.popBackStack() },
                onOpenCollection = { nav.navigate("${Routes.COLLEZIONE}/$it") },
            )
        }
        composable(
            route = "${Routes.COLLEZIONE}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            val id = it.arguments?.getString("id").orEmpty()
            CollezioneScreen(
                repo = repo,
                id = id,
                onBack = { nav.popBackStack() },
                onOpenPlate = { nav.navigate("${Routes.DETTAGLIO}/$it") },
            )
        }
        composable(Routes.GLOSSARIO) {
            GlossarioScreen(
                repo = repo,
                onBack = { nav.popBackStack() },
            )
        }
    }
}
