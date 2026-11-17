package com.lievito.crosta.scaffale.data

import com.lievito.crosta.scaffale.model.ShelfStatus

data class LibrarySnapshot(
    val favorites: Set<String>,
    val statuses: Map<String, ShelfStatus>,
)
