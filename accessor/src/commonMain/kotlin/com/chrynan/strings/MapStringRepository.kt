package com.chrynan.strings

import kotlin.String

class MapStringRepository(
    stringMap: Map<Key, String>,
    stringArrayMap: Map<Key, Array<String>>
) : StringRepository,
    MutableStringRepository {

    private val mutableStringMap = stringMap.toMutableMap()
    private val mutableStringArrayMap = stringArrayMap.toMutableMap()

    override fun getStringValue(resourceID: ResourceID, locale: String): String {
        val key = Key(resourceID = resourceID, locale = locale)

        return mutableStringMap[key] ?: throw StringResourceIDNotFoundException(
            resourceID = resourceID,
            locale = locale
        )
    }

    override fun getPluralStringValue(resourceID: PluralStringResourceID, locale: String, quantity: Quantity): String {
        val key = Key(resourceID = resourceID, locale = locale, quantity = quantity)

        return mutableStringMap[key] ?: throw StringResourceIDNotFoundException(
            resourceID = resourceID,
            locale = locale,
            quantity = quantity
        )
    }

    override fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String> {
        val key = Key(resourceID = resourceID, locale = locale)

        return mutableStringArrayMap[key] ?: throw StringResourceIDNotFoundException(
            resourceID = resourceID,
            locale = locale
        )
    }

    override fun updateStringValue(resourceID: ResourceID, locale: String, value: String) {
        val key = Key(resourceID = resourceID, locale = locale)

        mutableStringMap[key] = value
    }

    override fun updatePluralStringValues(
        resourceID: PluralStringResourceID,
        locale: String,
        values: Map<Quantity, String>
    ) {
        values.forEach {
            val key = Key(resourceID = resourceID, locale = locale, quantity = it.key)

            mutableStringMap[key] = it.value
        }
    }

    override fun updateStringArray(resourceID: StringArrayResourceID, locale: String, value: Array<String>) {
        val key = Key(resourceID = resourceID, locale = locale)

        mutableStringArrayMap[key] = value
    }

    data class Key(
        val resourceID: ResourceID,
        val locale: String,
        val quantity: Quantity? = null
    )
}