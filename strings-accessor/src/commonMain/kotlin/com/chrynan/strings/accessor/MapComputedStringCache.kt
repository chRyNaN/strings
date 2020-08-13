package com.chrynan.strings.accessor

class MapComputedStringCache : ComputedStringCache {

    override val entries: Set<ComputedStringCache.Entry>
        get() = computedValueCache.entries.map { ComputedStringCache.Entry(key = it.key, value = it.value) }.toSet()

    private val computedValueCache = mutableMapOf<ComputedStringCache.Key, String?>()

    override fun get(key: ComputedStringCache.Key): String? = computedValueCache[key]

    override fun set(key: ComputedStringCache.Key, value: String?) {
        computedValueCache[key] = value
    }
}