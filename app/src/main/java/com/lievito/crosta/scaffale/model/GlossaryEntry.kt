package com.lievito.crosta.scaffale.model

import kotlinx.serialization.Serializable

@Serializable
data class GlossaryEntry(
    val termine: String,
    val definizione: String,
)
