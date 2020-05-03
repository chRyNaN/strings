package com.chrynan.strings

interface ComputedStringCache {

    operator fun get(key: Key): String?

    operator fun set(key: Key, value: String?)

    data class Key(
        val resourceID: ResourceID,
        val locale: String,
        val quantity: Quantity? = null,
        val arguments: List<Any> = emptyList()
    )
}