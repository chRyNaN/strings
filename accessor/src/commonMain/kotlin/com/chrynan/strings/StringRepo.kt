package com.chrynan.strings

interface StringRepo {

    fun getStringValue(resourceID: ResourceID, locale: String): String

    fun getPluralStringValue(resourceID: ResourceID, locale: String, quantity: Quantity): String

    fun getStringArray(resourceID: StringArrayResourceID, locale: String): Array<String>
}