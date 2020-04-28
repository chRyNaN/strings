package com.chrynan.resources

import kotlin.String

class MapStringRepo(
    private val stringMap: Map<Key, String>,
    private val stringArrayMap: Map<Key, Array<String>>
) : StringRepo {

    override fun getStringValue(resourceID: ResourceID, locale: Locale): String {
        val key = Key(resourceID = resourceID, locale = locale)

        return stringMap[key] ?: throw StringResourceIDNotFoundException(resourceID = resourceID, locale = locale)
    }

    override fun getPluralStringValue(resourceID: ResourceID, locale: Locale, quantity: Quantity): String {
        val key = Key(resourceID = resourceID, locale = locale, quantity = quantity)

        return stringMap[key] ?: throw StringResourceIDNotFoundException(
            resourceID = resourceID,
            locale = locale,
            quantity = quantity
        )
    }

    override fun getStringArray(resourceID: StringArrayResourceID, locale: Locale): Array<String> {
        val key = Key(resourceID = resourceID, locale = locale)

        return stringArrayMap[key] ?: throw StringResourceIDNotFoundException(resourceID = resourceID, locale = locale)
    }

    data class Key(
        val resourceID: ResourceID,
        val locale: Locale,
        val quantity: Quantity? = null
    )
}