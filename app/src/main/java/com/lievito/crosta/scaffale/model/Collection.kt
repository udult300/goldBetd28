package com.lievito.crosta.scaffale.model

import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val id: String,
    val titolo: String,
    val descrizione: String,
    val plateIds: List<String>,
)
