package com.chrynan.resources

import kotlin.String

class MapStringCache(
    private val stringMap: Map<Key, String>,
    private val stringArrayMap: Map<Key, Array<String>>
) : StringCache {

    override fun getStringValue(resourceID: ResourceID, locale: Locale): String {
        val key = Key(resourceID = resourceID, locale = locale)

        return stringMap[key] ?: throw StringResourceIDNotFoundException(resourceID = resourceID, locale = locale)
    }

    override fun getStringArray(resourceID: StringArrayResourceID, locale: Locale): Array<String> {
        val key = Key(resourceID = resourceID, locale = locale)

        return stringArrayMap[key] ?: throw StringResourceIDNotFoundException(resourceID = resourceID, locale = locale)
    }

    data class Key(
        val resourceID: ResourceID,
        val locale: Locale
    )
}