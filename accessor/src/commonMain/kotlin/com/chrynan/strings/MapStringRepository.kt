package com.chrynan.strings

import kotlin.String

class MapStringRepository(
    private val stringMap: Map<Key, String>,
    private val stringArrayMap: Map<Key, Array<String>>
) : StringRepository {

    override fun getStringValue(resourceID: ResourceID, locale: String): String {
        val key = Key(resourceID = resourceID, locale = locale)

        return stringMap[key] ?: throw StringResourceIDNotFoundException(resourceID = resourceID, locale = locale)
    }

    override fun getPluralStringValue(resourceID: ResourceID, locale: String, quantity: Quantity): String {
        val key = Key(resourceID = resourceID, locale = locale, quantity = quantity)

        return stringMap[key] ?: throw StringResourceIDNotFoundException(
            resourceID = resourceID,
            locale = locale,
            quantity = quantity
        )
    }

    override fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String> {
        val key = Key(resourceID = resourceID, locale = locale)

        return stringArrayMap[key] ?: throw StringResourceIDNotFoundException(resourceID = resourceID, locale = locale)
    }

    data class Key(
        val resourceID: ResourceID,
        val locale: String,
        val quantity: Quantity? = null
    )
}