package com.chrynan.strings

class MapComputedStringCache : ComputedStringCache {

    private val computedValueCache = mutableMapOf<ComputedStringCache.Key, String?>()

    override fun get(key: ComputedStringCache.Key): String? = computedValueCache[key]

    override fun set(key: ComputedStringCache.Key, value: String?) {
        computedValueCache[key] = value
    }
}