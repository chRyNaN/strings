package com.chrynan.strings

interface StringRepository {

    fun getStringValue(resourceID: ResourceID, locale: String): String

    fun getPluralStringValue(resourceID: PluralStringResourceID, locale: String, quantity: Quantity): String

    fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String>
}