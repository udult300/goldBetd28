package com.lievito.crosta.scaffale.model

import kotlinx.serialization.Serializable

@Serializable
data class Plate(
    val id: String,
    val titolo: String,
    val sottotitolo: String,
    val area: String,
    val tecnica: String,
    val minuti: Int,
    val punti: List<String>,
    val testo: List<String>,
    val allergeni: String = "Contiene glutine.",
)
