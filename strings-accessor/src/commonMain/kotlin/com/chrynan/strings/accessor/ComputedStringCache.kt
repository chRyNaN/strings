package com.chrynan.strings.accessor

import com.chrynan.strings.Quantity
import com.chrynan.strings.ResourceID

interface ComputedStringCache {

    val entries: Set<Entry>

    operator fun get(key: Key): String?

    operator fun set(key: Key, value: String?)

    data class Key(
        val resourceID: ResourceID,
        val locale: String,
        val quantity: Quantity? = null,
        val arguments: List<Any> = emptyList()
    )

    data class Entry(
        val key: Key,
        val value: String?
    )
}